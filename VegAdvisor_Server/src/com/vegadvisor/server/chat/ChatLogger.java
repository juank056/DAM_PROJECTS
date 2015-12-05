/**
 * 
 */
package com.vegadvisor.server.chat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Clase para manejar Log del chat
 * 
 * @author Juan Camilo Mesa
 * 
 */
public class ChatLogger {

	/**
	 * Indica el nivel de Log con el que se va a escribir
	 */
	public static enum LEVEL {
		MANDATORY, DEBUG, INFO, WARN, ERROR, FATAL
	};

	/**
	 * Nivel de log configurado
	 */
	public static int configuredLevel;

	/**
	 * Cadenas de texto para log
	 */
	private static final String MANDATORY = "MANDATORY";

	/**
	 * Cadenas de texto para log
	 */
	private static final String DEBUG = "DEBUG";

	/**
	 * Cadenas de texto para log
	 */
	private static final String INFO = "INFO";

	/**
	 * Cadenas de texto para log
	 */
	private static final String WARN = "WARN";

	/**
	 * Cadenas de texto para log
	 */
	private static final String ERROR = "ERROR";

	/**
	 * Cadenas de texto para log
	 */
	private static final String FATAL = "FATAL";

	/**
	 * Archivo de log
	 */
	private static String logFile;

	/**
	 * Metodo para escribir en el log
	 * 
	 * @param event
	 *            el evento que se quiere escribir en el log
	 * @param level
	 *            el nivel de LOG.
	 */
	public static void log(String event, LEVEL level) {
		switch (level) {
		case MANDATORY:/* Siempre escribe para mandatorio */
			log(MANDATORY, event, false);
			break;
		case DEBUG:
			if (configuredLevel >= 5)
				log(DEBUG, event, false);
			break;
		case INFO:
			if (configuredLevel >= 4)
				log(INFO, event, false);
			break;
		case WARN:
			if (configuredLevel >= 3)
				log(WARN, event, true);
			break;
		case ERROR:
			if (configuredLevel >= 2)
				log(ERROR, event, true);
			break;
		case FATAL:
			if (configuredLevel >= 1)
				log(FATAL, event, true);
			break;
		}
	}

	/**
	 * Metodo para escribir ya en el log Escribe de la manera que deba ser.
	 * 
	 * @param event
	 *            El evento a escribir
	 * 
	 */
	private synchronized static void log(String level, String event, boolean bad) {
		// Obtiene la fecha en formato string
		String dateString = getCurrentDateTime();
		// Salida a impresion
		String out = level + " (" + dateString + "): " + event;
		// Escribe el evento en consola
		if (!bad) {/* Escribe en salida estandar */
			System.out.println(out);
		} else {/* Escribe en error estandar */
			System.err.println(out);
		}
		// Escribe el evento en el archivo de log
		writeInLogFile(dateString.substring(0, 10), out);
	}

	/**
	 * Se encarga de escribir un evento en un archivo de log
	 * 
	 * @param dateString
	 *            la fecha del sistema
	 * @param event
	 *            el evento a escribir
	 */
	private static void writeInLogFile(String dateString, String event) {
		// Obtiene el nombre del archivo en el cual se va a escribir
		String fileName = logFile + dateString + ".log";
		// Abre el archivo
		try {
			FileWriter fw = new FileWriter(fileName, true);/*
															 * Para escribir al
															 * final del archivo
															 */
			// Crea print writer
			PrintWriter pw = new PrintWriter(fw);
			// Escribe linea de log
			pw.println(event);
			// Cierra el archivo
			fw.close();
			pw.close();
		} catch (IOException e) {
			System.out.println("Unable to open log file");
		}
		// Asigna Cadena a Null para que sea eliminada
		event = null;
	}

	/**
	 * Configura Log
	 */
	public static void configureLog(String file, int level) {
		logFile = file;
		if (level < 1 || level > 5)
			configuredLevel = 1;/* Menor nivel de detalle */
		else
			configuredLevel = level;
	}

	/**
	 * Obtiene Fechs y hora actual del sistema en formato YYYY-MM-DD HH:MM:SS
	 * 
	 * @return
	 */
	private static String getCurrentDateTime() {
		// Fecha actual
		Date currDate = new Date();
		// Retorna
		return getDateString(currDate) + " " + getTimeString(currDate);
	}

	/**
	 * Obtiene la fecha en string YYYY-MM-DD
	 * 
	 * @param date
	 *            la fecha a obtener
	 * @return fecha en String
	 */
	@SuppressWarnings("deprecation")
	private static String getDateString(Date date) {
		String ret = "" + (date.getYear() + 1900);
		ret += date.getMonth() + 1 > 9 ? "-" + (date.getMonth() + 1) : "-0"
				+ (date.getMonth() + 1);
		ret += date.getDate() > 9 ? "-" + date.getDate() : "-0"
				+ date.getDate();
		return ret;
	}

	/**
	 * Obtiene la hora en string HH:MM:SS
	 * 
	 * @param date
	 *            la hora a obtener
	 * @return hora en String
	 */
	@SuppressWarnings("deprecation")
	private static String getTimeString(Date date) {
		String ret = date.getHours() > 9 ? "" + date.getHours() : "0"
				+ date.getHours();
		ret += date.getMinutes() > 9 ? ":" + date.getMinutes() : ":0"
				+ date.getMinutes();
		ret += date.getSeconds() > 9 ? ":" + date.getSeconds() : ":0"
				+ date.getSeconds();
		return ret;
	}

}
