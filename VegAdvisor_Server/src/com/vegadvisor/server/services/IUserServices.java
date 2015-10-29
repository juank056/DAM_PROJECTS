/**
 * 
 */
package com.vegadvisor.server.services;

import java.util.Date;

/**
 * @author JuanCamilo
 *
 */
public interface IUserServices {

	/**
	 * M�todo para validar un usuario y su contrase�a
	 * 
	 * @param userId
	 *            Id de usuario
	 * @param password
	 *            Contrase�a
	 * @return String[0] = Indicador de Validaci�n (0,1). String[1] = Nombre del
	 *         usuario
	 */
	public String[] validateUser(String userId, String password);

	/**
	 * M�todo para crear un nuevo usuario en el sistema
	 * 
	 * @param userId
	 *            Id del usuario a crear
	 * @param userName
	 *            Nombre de usuario
	 * @param userLastName
	 *            Apellido del usuario
	 * @param email
	 *            Email del usuario
	 * @param password
	 *            Contrase�a del usuario
	 * @return String[0] = Indicador de validaci�n(0,1). String[1] = Mensaje de
	 *         validaci�n
	 */
	public String[] createUser(String userId, String userName,
			String userLastName, String email, String password);

	/**
	 * M�todo para actualizar los datos de un usuario
	 * 
	 * @param userId
	 *            Id del usuario a crear
	 * @param userName
	 *            Nombre de usuario
	 * @param userLastName
	 *            Apellido del usuario
	 * @param email
	 *            Email del usuario
	 * @param password
	 *            Contrase�a del usuario
	 * @param dateOfBirth
	 *            Fecha de nacimiento
	 * @param countryCode
	 *            C�digo de pais
	 * @param cityCode
	 *            C�digo de ciudad
	 * @param isVegan
	 *            Indicador de vegano
	 * @param hobbies
	 *            Hobbies de la persona
	 * @return String[0] = Indicador de validaci�n(0,1). String[1] = Mensaje de
	 *         validaci�n
	 */
	public String[] updateUser(String userId, String userName,
			String userLastName, String email, String password,
			Date dateOfBirth, String countryCode, String cityCode,
			String isVegan, String hobbies);

	/**
	 * M�todo para registrar el check in de un usuario dentro de un
	 * establecimiento
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param establishmentId
	 *            Id del establecimiento
	 * @return String[0] = Indicador de validaci�n(0,1). String[1] = Mensaje de
	 *         validaci�n
	 */
	public String[] checkInUser(String userId, String establishmentId);

}