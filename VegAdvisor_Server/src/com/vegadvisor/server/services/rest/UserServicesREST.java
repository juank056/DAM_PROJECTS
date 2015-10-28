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

import org.jpos.util.Log;

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
				Log.DEBUG);
		// Validamos usuario
		String[] response = userServices.validateUser(userId, password);
		LogLogger.getInstance(getClass()).logger("Finaliza validateUser",
				Log.DEBUG);
		// Retornamos
		return new ReturnValidation(response[0], response[1]);
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
		LogLogger.getInstance(getClass())
				.logger("Inicia createUser", Log.DEBUG);
		// Creación usuario
		String[] response = userServices.createUser(userId, userName,
				userLastName, email, password);
		LogLogger.getInstance(getClass()).logger("Finaliza createUser",
				Log.DEBUG);
		// Retornamos
		return new ReturnValidation(response[0], response[1]);
	}

}
