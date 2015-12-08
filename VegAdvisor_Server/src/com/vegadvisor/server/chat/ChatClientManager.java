/**
 * 
 */
package com.vegadvisor.server.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Clase que maneja la conexión con un cliente especifico
 * 
 * @author JuanCamilo
 *
 */
public class ChatClientManager implements Runnable {

	/**
	 * Socket de conexión con cliente
	 */
	private Socket socket;

	/**
	 * Id del usuario
	 */
	private String userId;

	/**
	 * Manager
	 */
	private ChatServerManager manager;

	/**
	 * Cola de mensajes para notificar al movil
	 */
	private BlockingQueue<String> messages;

	/**
	 * Data Input Stream del socket
	 */
	private DataInputStream inputStream;

	/**
	 * Data Output Stream del socket
	 */
	private DataOutputStream outputStream;

	/**
	 * Shutdown
	 */
	private boolean shutdown = false;

	/**
	 * Constructor
	 * 
	 * @param socket
	 *            Socket del usuario
	 * @param userId
	 *            Id del usuario
	 * @param manager
	 *            Clase padre Manager
	 */
	public ChatClientManager(Socket socket, String userId,
			ChatServerManager manager) {
		this.socket = socket;
		this.userId = userId;
		this.manager = manager;
		// Inicia cola de mensajes
		this.messages = new LinkedBlockingQueue<String>();
		// Input y output stream del socket
		try {
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			ChatLogger.log("Error inicializing ChatClientManager. UserId: "
					+ userId, ChatLogger.LEVEL.ERROR);
			e.printStackTrace();
		}
	}

	/**
	 * Constructor sin parametros
	 */
	public ChatClientManager() {

	}

	/**
	 * Inicia Ejecución
	 */
	@Override
	public void run() {
		try {
			// Inicia Output router para envio de notificaciones
			OutputRouter outputRouter = new OutputRouter();
			// Lo arranca
			new Thread(outputRouter).start();
			// Inicia ciclo para leer
			while (!shutdown) {
				// Lee longitud del id del usuario
				int length = inputStream.readInt();
				// Crea buffer del tamaño
				byte[] buffer = new byte[length];
				// Lee bytes del nombre de usuario
				inputStream.read(buffer);
				// Nombre de usuario
				String userIdTo = new String(buffer);
				// Notifica
				manager.notifyClient(userId, userIdTo);
			}
		} catch (Exception e) {/* Ocurrio algun error en el proceso */
			// Escribe error
			ChatLogger.log("Error executing chat Client Manager. UserId: "
					+ userId + " Message: " + e.getMessage(),
					ChatLogger.LEVEL.ERROR);
			e.printStackTrace();
		}
		// Cierra socket
		try {
			socket.close();
		} catch (Exception e) {
			ChatLogger.log("Error Closing socket. UserId: " + userId
					+ " Message: " + e.getMessage(), ChatLogger.LEVEL.ERROR);
			e.printStackTrace();
		}
	}

	/**
	 * @return the messages
	 */
	public BlockingQueue<String> getMessages() {
		return messages;
	}

	/***************************************************
	 * OUTPUT ROUTER PARA ENVIO DE MENSAJES HACIA AFUERA
	 **************************************************/
	/**
	 * Thread Interno para manejar los mensajes de salida. Este Thread debe leer
	 * de la cola de OutputMessages y encargarse del envio de estos mensajes
	 * 
	 * @author Juan Camilo
	 */
	private class OutputRouter implements Runnable {

		/**
		 * Constructor. No recibe ningun parametro. Todo lo toma de su clase
		 * padre
		 */
		public OutputRouter() {
			ChatLogger.log("Creating Output Router for user: " + userId,
					ChatLogger.LEVEL.DEBUG);
		}

		/**
		 * Procesa los mensajes de salida
		 */
		@Override
		public void run() {
			ChatLogger.log("Executing Output Router. User Id: " + userId,
					ChatLogger.LEVEL.DEBUG);
			// Debe leer de la cola de mensajes de salida
			while (!shutdown) {
				try {
					// Obtiene mensaje a enviar
					String message = messages.take();
					// Escribe longitud del mensaje
					outputStream.writeInt(message.length());
					// Escribe mensaje
					outputStream.write(message.getBytes());
				} catch (Exception e) {
					ChatLogger.log(
							"Error while executing output Router: "
									+ e.getMessage(), ChatLogger.LEVEL.ERROR);
					e.printStackTrace();
				}
			}
			ChatLogger.log("This Output Router is going down. UserId: "
					+ userId, ChatLogger.LEVEL.DEBUG);
		}
	}

}
