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

import com.vegadvisor.server.persistence.bo.Chdmensa;
import com.vegadvisor.server.persistence.bo.Usmusuar;
import com.vegadvisor.server.services.IChatServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios Rest de Chat
 * 
 * @author JuanCamilo
 *
 */
@Path("/chat")
public class ChatServicesREST {

	/**
	 * Servicios de Chat
	 */
	private IChatServices chatServices;

	/**
	 * Constructor
	 */
	public ChatServicesREST() {
		// Inicia servicios
		chatServices = SpringAppContext.getAppContext().getBean(
				IChatServices.class);
	}

	/**
	 * Busca usuarios activos para iniciar chat
	 * 
	 * @param clue
	 *            Pista de nombre de usuario a buscar
	 * @return Lista de usuarios activos para chat
	 */
	@POST
	@Path("/findChatUsers")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usmusuar> findChatUsers(@FormParam("clue") String clue) {
		LogLogger.getInstance(getClass()).logger("Inicia findChatUsers",
				LogLogger.DEBUG);
		// Ejecuta servicio
		List<Usmusuar> response = chatServices.findChatUsers(clue);
		LogLogger.getInstance(getClass()).logger("Finaliza findChatUsers",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Recolecta mensajes de chat pendientes de un usuario
	 * 
	 * @param userId
	 *            Usuario del que se van a recolectar los mensajes de chat
	 * @return Mensajes de chat pendientes del usuario
	 */
	@POST
	@Path("/recolectChatMessages")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Chdmensa> recolectChatMessages(
			@FormParam("userId") String userId) {
		LogLogger.getInstance(getClass()).logger("Inicia recolectChatMessages",
				LogLogger.DEBUG);
		// Ejecuta servicio
		List<Chdmensa> response = chatServices.recolectChatMessages(userId);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza recolectChatMessages", LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Registra mensaje de chat
	 * 
	 * @param userIdFrom
	 *            Usuario que envia el mensaje
	 * @param userIdTo
	 *            Usuario que recibirá el mensaje
	 * @param content
	 *            Contenido del mensaje
	 * @return Retorno de validación
	 */
	@POST
	@Path("/registerChatMessage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation registerChatMessage(
			@FormParam("userIdFrom") String userIdFrom,
			@FormParam("userIdTo") String userIdTo,
			@FormParam("content") String content) {
		LogLogger.getInstance(getClass()).logger("Inicia registerChatMessage",
				LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = chatServices.registerChatMessage(
				userIdFrom, userIdTo, content);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza registerChatMessage", LogLogger.DEBUG);
		// Retornamos
		return response;
	}
}
