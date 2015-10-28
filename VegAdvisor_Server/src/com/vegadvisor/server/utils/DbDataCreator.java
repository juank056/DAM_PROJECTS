/**
 * 
 */
package com.vegadvisor.server.utils;

/**
 * Encripta datos para ser usados en los archivos de configuracion
 * 
 * @author Administrador
 * 
 */
public class DbDataCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String passwd = "";
		String crypted = DBDataChiper.encrypt(passwd);
		System.out.println("Crypted: "+crypted);
		String clear = DBDataChiper.decrypt(crypted);
		System.out.println("Clear: "+clear);

	}

}
