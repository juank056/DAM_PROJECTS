/**
 * 
 */
package com.vegadvisor.server.services;

/**
 * @author JuanCamilo
 *
 */
public interface UserServices {

	/**
	 * Método para validar un usuario y su contraseña
	 * 
	 * @param userName
	 *            Nombre de usuario
	 * @param password
	 *            Contraseña
	 * @return String[0] = Indicador de Validación (0,1). String[1] = Nombre del
	 *         usuario
	 */
	public String[] validateUser(String userName, String password);

}
