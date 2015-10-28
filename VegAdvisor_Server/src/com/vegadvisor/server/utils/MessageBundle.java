package com.vegadvisor.server.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.utils.exception.MissingConfigurationParameterException;

/**
 * Clase para manejar los mensajes de la aplicacion
 * 
 * @author ESFDES27
 * 
 */
public final class MessageBundle {

	/**
	 * Indica la ruta donde se encuentra el archivo de mensajes de la aplicacion
	 * unica ruta en codigo "quemada".
	 */
	private static final String MESSAGE_FILE = "../conf/messages.properties";
	private static Map<String, String> messages;
	static {
		reloadParameters();
	} // static

	/**
	 * Constructor privado
	 */
	private MessageBundle() {
	}

	/**
	 * Metodo para obtener el valor de un mensaje
	 * 
	 * @param name
	 *            el nombre del mensaje a obtener
	 * @return el valor del mensaje.
	 * @throws MissingConfigurationParameterException
	 *             en caso de no encontrar el parametro
	 */
	public static String getMessage(String name) {
		String value = messages.get(name);
		if (value == null) {/* Mensaje no encontrado */
			return name;
		}
		return value;
	}

	/**
	 * Regarga los parametros del archivo de configuracion
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void reloadParameters() {
		String message_path;
		try {
			message_path = ConfigApplicationManager
					.getParameter("com.vegadvisor.messages");
		} catch (MissingConfigurationParameterException e1) {/* No encontrado */
			// Usa ruta default
			message_path = MESSAGE_FILE;
		}
		try {
			/* Read property file (if exists). */
			Properties properties = new Properties();
			try {
				properties.load(new FileInputStream(message_path));
				/*
				 * We use a "HashMap" instead of a "HashTable" because HashMap's
				 * methods are *not* synchronized (so they are faster), and the
				 * parameters are only read.
				 */
				messages = new HashMap(properties);
			} catch (Exception e) {
				messages = new HashMap<String, String>();
				System.out.println("Input stream null. Messages not loaded");
				System.out.println(ExceptionUtils.getFullStackTrace(e));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} // class

