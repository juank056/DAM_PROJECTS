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

import com.vegadvisor.server.persistence.bo.Fomhilfo;
import com.vegadvisor.server.services.IForumServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios Rest de Foro
 * 
 * @author JuanCamilo
 *
 */
@Path("/forum")
public class ForumServicesREST {

	/**
	 * Servicios de Foro
	 */
	private IForumServices forumServices;

	/**
	 * Constructor
	 */
	public ForumServicesREST() {
		// Inicia servicios
		forumServices = SpringAppContext.getAppContext().getBean(
				IForumServices.class);
	}

	/**
	 * Inicia un nuevo hilo de foro
	 * 
	 * @param userId
	 *            Uuario que inicia el hilo
	 * @param threadTitle
	 *            Titulo del hilo
	 * @param threadDetail
	 *            Detalle del hilo
	 * @return Retorno de validación
	 */
	@POST
	@Path("/createForumThread")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation createForumThread(
			@FormParam("userId") String userId,
			@FormParam("threadTitle") String threadTitle,
			@FormParam("threadDetail") String threadDetail) {
		LogLogger.getInstance(getClass()).logger("Inicia createForumThread",
				LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = forumServices.createForumThread(userId,
				threadTitle, threadDetail);
		LogLogger.getInstance(getClass()).logger("Finaliza createForumThread",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Crea una respuesta a un hilo del foro
	 * 
	 * @param userId
	 *            Id del usuario que da la respuesta
	 * @param threadId
	 *            Id del hilo de foro
	 * @param responseDetail
	 *            Detalle de la respuesta
	 * @return Retorno de validación
	 */
	@POST
	@Path("/createForumThreadResponse")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation createForumThreadResponse(
			@FormParam("userId") String userId,
			@FormParam("threadId") int threadId,
			@FormParam("responseDetail") String responseDetail) {
		LogLogger.getInstance(getClass()).logger(
				"Inicia createForumThreadResponse", LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = forumServices.createForumThreadResponse(
				userId, threadId, responseDetail);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza createForumThreadResponse", LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Busca hilos de foro de acuerdo a titulo
	 * 
	 * @param clue
	 *            Pista de hilos de foro
	 * @return hilos de foro segun pista
	 */
	@POST
	@Path("/findForumThreads")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fomhilfo> findForumThreads(@FormParam("clue") String clue) {
		LogLogger.getInstance(getClass()).logger("Inicia findForumThreads",
				LogLogger.DEBUG);
		// Ejecuta servicio
		List<Fomhilfo> response = forumServices.findForumThreads(clue);
		LogLogger.getInstance(getClass()).logger("Finaliza findForumThreads",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}
}
