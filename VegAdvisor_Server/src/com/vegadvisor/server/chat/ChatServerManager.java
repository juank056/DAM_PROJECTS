/**
 * 
 */
package com.vegadvisor.server.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase principal para obtener conexion con los dispositivos moviles y
 * nofificar de nuevos mensajes de chat
 * 
 * @author JuanCamilo
 *
 */
public class ChatServerManager {

	/**
	 * Numero de puerto por defecto
	 */
	private static final int DEFAULT_PORT = 9090;

	/**
	 * Ruta de Log por defecto
	 */
	private static final String DEFAULT_LOG_FILE = "log/log_ChatVegadvisor_";

	/**
	 * Nivel de log por defecto
	 */
	private static final int DEFAULT_LOG_LEVEL = 5;

	/**
	 * Puerto configurado
	 */
	private static int port;

	/**
	 * Nivel de log
	 */
	private static int log_level;

	/**
	 * Archivo de Log
	 */
	private static String log_file;

	/**
	 * Shutdown
	 */
	private static boolean shutdown = false;

	/**
	 * Instancia
	 */
	private static ChatServerManager instance;

	/**
	 * Mapa de conexiones
	 */
	private Map<String, ChatClientManager> connectionMap;

	/**
	 * Obtiene la instancia del ChatServerManager
	 * 
	 * @return la instancia en ejecución del ChatServerManager
	 */
	public static ChatServerManager getInstance() {
		if (instance == null)
			instance = new ChatServerManager();
		return instance;
	}

	/**
	 * Constructor del ChatServerManager
	 */
	private ChatServerManager() {
		// Inicia mapa de conexiones
		this.connectionMap = new HashMap<String, ChatClientManager>();
	}

	/**
	 * Recibe como parametro el puerto por donde debe escuchar
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// Revisa si ha llegado puerto
			assignParameters(args);
			// Obtiene instancia
			getInstance();
			// Inicia Logger
			ChatLogger.configureLog(log_file, log_level);
			// Crea server socket para iniciar ciclo
			serverSocket = new ServerSocket(port);
			// Inicia ciclo para
			while (!shutdown) {
				try {
					ChatLogger.log("Expecting connection...",
							ChatLogger.LEVEL.DEBUG);
					// Acepta conexión
					Socket socket = serverSocket.accept();
					// Inicia nuevo hilo para que atienda al cliente
					// Inicia input stream para leer datos
					DataInputStream dis = new DataInputStream(
							socket.getInputStream());
					// Lee longitud del id del usuario
					int length = dis.readInt();
					// Crea buffer del tamaño
					byte[] buffer = new byte[length];
					// Lee bytes del nombre de usuario
					dis.read(buffer);
					// Nombre de usuario
					String userId = new String(buffer);
					ChatLogger.log("Connection Accepted. User: " + userId,
							ChatLogger.LEVEL.DEBUG);
					// Crea nuevo hilo para atender al usuario
					ChatClientManager client = new ChatClientManager(socket,
							userId, getInstance());
					// Adiciona cliente al mapa
					getInstance().connectionMap.put(userId, client);
					// Inicia client
					new Thread(client).start();
				} catch (Exception e) {/* Ocurrio algun error en el proceso */
					// Escribe error
					ChatLogger.log(
							"Error establishing connection with client: "
									+ e.getMessage(), ChatLogger.LEVEL.ERROR);
					e.printStackTrace();
				}
			}
			// Cierra server socket
			serverSocket.close();
		} catch (Exception e) {/* Ocurrio algun error en el proceso */
			// Escribe error
			ChatLogger.log("Error while executing Manager: " + e.getMessage(),
					ChatLogger.LEVEL.ERROR);
			e.printStackTrace();
			// Cierra server socket
			try {
				serverSocket.close();
			} catch (IOException e1) {/* Error cerrando socket */
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Metodo para enviar mensaje a cliente
	 * 
	 * @param userId
	 *            User Id
	 */
	public void notifyClient(String userIdFrom, String userIdTo) {
		ChatLogger.log("Notifying user: " + userIdTo + ". From: " + userIdFrom,
				ChatLogger.LEVEL.DEBUG);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Revisa si encuentra al usuario en el mapa
		ChatClientManager client = connectionMap.get(userIdTo);
		if (client != null) {/* Cliente esta en el mapa */
			// Escribe en la cola del cliente para que notifique al movil
			client.getMessages().offer(userIdFrom);
		}
	}

	/**
	 * Obtiene parametros recibidos para la ejecucion
	 * 
	 * @param args
	 */
	private static void assignParameters(String[] args) {
		// Todos los datos a default inicialmente
		port = DEFAULT_PORT;
		log_level = DEFAULT_LOG_LEVEL;
		log_file = DEFAULT_LOG_FILE;
		// Recorre parametros en busca de los valores
		for (int i = 0; i < args.length; i++) {
			// Identificador del parámetro
			String id = args[i].split("=")[0];
			// Valor del parámetro
			String paramValue = args[i].split("=")[1];
			// Comienza a comparar con los esperados
			if (id.equals("PO")) {/* Puerto */
				port = Integer.valueOf(paramValue);
			} else if (id.equals("LL")) {/* Nivel de Log */
				log_level = Integer.valueOf(paramValue);
			} else if (id.equals("LF")) {/* Log File */
				log_file = paramValue;
			}
		}
	}
}
