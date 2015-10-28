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
	 * M�todo para validar un usuario y su contrase�a
	 * 
	 * @param userName
	 *            Nombre de usuario
	 * @param password
	 *            Contrase�a
	 * @return String[0] = Indicador de Validaci�n (0,1). String[1] = Nombre del
	 *         usuario
	 */
	public String[] validateUser(String userName, String password);

}
