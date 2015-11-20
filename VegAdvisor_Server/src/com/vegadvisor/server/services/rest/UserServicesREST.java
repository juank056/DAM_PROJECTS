/**
 * 
 */
package com.vegadvisor.server.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vegadvisor.server.persistence.bo.Usmusuar;
import com.vegadvisor.server.services.IUserServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios Rest de usuarios
 * 
 * @author JuanCamilo
 *
 */
@Path("/user")
public class UserServicesREST {

	/**
	 * Servicios de usuario
	 */
	private IUserServices userServices;

	/**
	 * Constructor
	 */
	public UserServicesREST() {
		// Inicia servicios de usuario
		userServices = SpringAppContext.getAppContext().getBean(
				IUserServices.class);
	}

	/**
	 * Busca un usuario por su Id
	 * 
	 * @param userId
	 *            Id del usuario a buscar
	 * @return Registro del usuario
	 */
	@POST
	@Path("/findUserById")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Usmusuar findUserById(@FormParam("userId") String userId) {
		LogLogger.getInstance(getClass()).logger("Inicia findUserById",
				LogLogger.DEBUG);
		// Validamos usuario
		Usmusuar response = userServices.findUserById(userId);
		LogLogger.getInstance(getClass()).logger("Finaliza findUserById",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Método para validar un usuario
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param password
	 *            Contraseña del usuario
	 * @return Retorno de validación
	 */
	@POST
	@Path("/validateUser")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation validateUser(@FormParam("userId") String userId,
			@FormParam("password") String password) {
		LogLogger.getInstance(getClass()).logger("Inicia validateUser",
				LogLogger.DEBUG);
		// Validamos usuario
		ReturnValidation response = userServices.validateUser(userId, password);
		LogLogger.getInstance(getClass()).logger("Finaliza validateUser",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Método para crear un usuario en el sistema
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param userName
	 *            Nombre del usuario
	 * @param userLastName
	 *            Apellido del usuario
	 * @param email
	 *            email del usuario
	 * @param password
	 *            contraseña del usuario
	 * @return Indicador de validación
	 */
	@POST
	@Path("/createUser")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation createUser(@FormParam("userId") String userId,
			@FormParam("userName") String userName,
			@FormParam("userLastName") String userLastName,
			@FormParam("email") String email,
			@FormParam("password") String password) {
		LogLogger.getInstance(getClass()).logger("Inicia createUser",
				LogLogger.DEBUG);
		// Creación usuario
		ReturnValidation response = userServices.createUser(userId, userName,
				userLastName, email, password);
		LogLogger.getInstance(getClass()).logger("Finaliza createUser",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Actualiza usuario del sistema
	 * 
	 * @param userId
	 *            Id del usuario a actualizar
	 * @param userName
	 *            nombre del usuario
	 * @param userLastName
	 *            apellido del usuarii
	 * @param email
	 *            Email del usuario
	 * @param password
	 *            contraseña del usuario
	 * @param dateOfBirth
	 *            Fecha de nacimiento (Formato YYYYMMDD)
	 * @param countryCode
	 *            Código de pais
	 * @param cityCode
	 *            Código de ciudad
	 * @param isVegan
	 *            Indicador de vegano
	 * @param hobbies
	 *            Hobbies
	 * @param gender
	 *            Género
	 * @return Retorno de validación
	 */
	@POST
	@Path("/updateUser")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation updateUser(@FormParam("userId") String userId,
			@FormParam("userName") String userName,
			@FormParam("userLastName") String userLastName,
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("dateOfBirth") String dateOfBirth,
			@FormParam("countryCode") String countryCode,
			@FormParam("cityCode") String cityCode,
			@FormParam("isVegan") String isVegan,
			@FormParam("hobbies") String hobbies,
			@FormParam("gender") String gender) {
		LogLogger.getInstance(getClass()).logger("Inicia updateUser",
				LogLogger.DEBUG);
		// Actualizacion usuario
		ReturnValidation response = userServices.updateUser(userId, userName,
				userLastName, email, password,
				DateUtils.getDateDateYYYYMMDD(dateOfBirth), countryCode,
				cityCode, isVegan, hobbies, gender);
		LogLogger.getInstance(getClass()).logger("Finaliza updateUser",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	@POST
	@Path("/checkInUser")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation checkInUser(@FormParam("userId") String userId,
			@FormParam("establishmentId") int establishmentId) {
		LogLogger.getInstance(getClass()).logger("Inicia checkInUser",
				LogLogger.DEBUG);
		// Checkin de uuario en establecimiento
		ReturnValidation response = userServices.checkInUser(userId,
				establishmentId);
		LogLogger.getInstance(getClass()).logger("Finaliza checkInUser",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

}
