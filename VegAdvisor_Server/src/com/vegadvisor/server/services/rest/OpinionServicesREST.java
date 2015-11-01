/**
 * 
 */
package com.vegadvisor.server.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vegadvisor.server.persistence.bo.Esdopies;
import com.vegadvisor.server.services.IOpinionServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios Rest de Opinion
 * 
 * @author JuanCamilo
 *
 */
@Path("/opinion")
public class OpinionServicesREST {

	/**
	 * Servicios de opinion
	 */
	private IOpinionServices opinionServices;

	/**
	 * Constructor
	 */
	public OpinionServicesREST() {
		// Inicia servicios
		opinionServices = SpringAppContext.getAppContext().getBean(
				IOpinionServices.class);
	}

	/**
	 * Obtiene las opiniones de un establecimiento
	 * 
	 * @param establishmentId
	 *            id del establecimiento
	 * @param maxOpinions
	 *            Maximo de opiniones a obtener
	 * @return Lista de opiniones de un establecimiento
	 */
	@POST
	@Path("/findEstablishmentsOpinions")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Esdopies> findEstablishmentsOpinions(
			@FormParam("establishmentId") int establishmentId,
			@FormParam("maxOpinions") int maxOpinions) {
		LogLogger.getInstance(getClass()).logger(
				"Inicia findEstablishmentsOpinions", LogLogger.DEBUG);
		// Ejecuta servicio
		List<Esdopies> response = opinionServices.findEstablishmentsOpinions(
				establishmentId, maxOpinions);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza findEstablishmentsOpinions", LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Registra una opinion de un usuario
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param userId
	 *            Id del usuario
	 * @param stars
	 *            Numero de estrellas de la opinión
	 * @param opinion
	 *            Detalle de la opinión
	 * @return Retorno de validación
	 */
	@POST
	@Path("/registerUsersOpinion")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation registerUsersOpinion(
			@FormParam("establishmentId") int establishmentId,
			@FormParam("userId") String userId, @FormParam("stars") int stars,
			@FormParam("opinion") String opinion) {
		LogLogger.getInstance(getClass()).logger("Inicia registerUsersOpinion",
				LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = opinionServices.registerUsersOpinion(
				establishmentId, userId, stars, opinion);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza registerUsersOpinion", LogLogger.DEBUG);
		// Retornamos
		return response;
	}

}
