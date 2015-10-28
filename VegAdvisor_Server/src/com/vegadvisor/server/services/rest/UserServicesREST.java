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

import com.vegadvisor.server.services.IUserServices;
import com.vegadvisor.server.services.rest.bo.ReturnValidation;
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
	 * M�todo para validar un usuario
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param password
	 *            Contrase�a del usuario
	 * @return Retorno de validaci�n
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
		String[] response = userServices.validateUser(userId, password);
		LogLogger.getInstance(getClass()).logger("Finaliza validateUser",
				LogLogger.DEBUG);
		// Retornamos
		return new ReturnValidation(response[0], response[1]);
	}

	/**
	 * M�todo para crear un usuario en el sistema
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
	 *            contrase�a del usuario
	 * @return Indicador de validaci�n
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
		// Creaci�n usuario
		String[] response = userServices.createUser(userId, userName,
				userLastName, email, password);
		LogLogger.getInstance(getClass()).logger("Finaliza createUser",
				LogLogger.DEBUG);
		// Retornamos
		return new ReturnValidation(response[0], response[1]);
	}

}
