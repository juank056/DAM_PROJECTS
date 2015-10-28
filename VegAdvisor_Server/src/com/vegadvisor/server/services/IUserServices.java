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
	 * Método para validar un usuario y su contraseña
	 * 
	 * @param userId
	 *            Id de usuario
	 * @param password
	 *            Contraseña
	 * @return String[0] = Indicador de Validación (0,1). String[1] = Nombre del
	 *         usuario
	 */
	public String[] validateUser(String userId, String password);

	/**
	 * Método para crear un nuevo usuario en el sistema
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
	 *            Contraseña del usuario
	 * @return String[0] = Indicador de validación(0,1). String[1] = Mensaje de
	 *         validación
	 */
	public String[] createUser(String userId, String userName,
			String userLastName, String email, String password);

	/**
	 * Método para actualizar los datos de un usuario
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
	 *            Contraseña del usuario
	 * @param dateOfBirth
	 *            Fecha de nacimiento
	 * @param countryCode
	 *            Código de pais
	 * @param cityCode
	 *            Código de ciudad
	 * @param isVegan
	 *            Indicador de vegano
	 * @param hobbies
	 *            Hobbies de la persona
	 * @return String[0] = Indicador de validación(0,1). String[1] = Mensaje de
	 *         validación
	 */
	public String[] updateUser(String userId, String userName,
			String userLastName, String email, String password,
			Date dateOfBirth, String countryCode, String cityCode,
			String isVegan, String hobbies);

	/**
	 * Método para registrar el check in de un usuario dentro de un
	 * establecimiento
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param establishmentId
	 *            Id del establecimiento
	 * @return String[0] = Indicador de validación(0,1). String[1] = Mensaje de
	 *         validación
	 */
	public String[] checkInUser(String userId, String establishmentId);

}
