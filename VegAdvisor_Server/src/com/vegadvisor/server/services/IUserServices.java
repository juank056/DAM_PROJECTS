/**
 * 
 */
package com.vegadvisor.server.services;

import java.util.Date;

import com.vegadvisor.server.persistence.bo.Usmusuar;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * Interfaz que define los servicios relacionados con los usuarios del sistema
 * 
 * @author JuanCamilo
 *
 */
public interface IUserServices {
	
	/**
	 * Metodo para obtener un usuario por su Id
	 * @param userId Id del usuario a buscar
	 * @return Datos del usuario
	 */
	public Usmusuar findUserById(String userId);

	/**
	 * M�todo para validar un usuario y su contrase�a
	 * 
	 * @param userId
	 *            Id de usuario
	 * @param password
	 *            Contrase�a
	 * @return Indicador de validaci�n
	 */
	public ReturnValidation validateUser(String userId, String password);

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
	 * @return Indicador de validaci�n
	 */
	public ReturnValidation createUser(String userId, String userName,
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
	 * @param gender
	 *            Genero de la persona
	 * @return Indicador de validaci�n
	 */
	public ReturnValidation updateUser(String userId, String userName,
			String userLastName, String email, String password,
			Date dateOfBirth, String countryCode, String cityCode,
			String isVegan, String hobbies, String gender);

	/**
	 * M�todo para registrar el check in de un usuario dentro de un
	 * establecimiento
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param establishmentId
	 *            Id del establecimiento
	 * @return Indicador de validaci�n
	 */
	public ReturnValidation checkInUser(String userId, int establishmentId);

}
