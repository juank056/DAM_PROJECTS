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

import com.vegadvisor.server.persistence.bo.Evmevent;
import com.vegadvisor.server.services.IEventServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios Rest de Eventos
 * 
 * @author JuanCamilo
 *
 */
@Path("/event")
public class EventServicesREST {

	/**
	 * Servicios de Eventos
	 */
	private IEventServices eventServices;

	/**
	 * Constructor
	 */
	public EventServicesREST() {
		// Inicia servicios
		eventServices = SpringAppContext.getAppContext().getBean(
				IEventServices.class);
	}

	/**
	 * Método para crear un evento
	 * 
	 * @param userId
	 *            Usuario que crea el evento
	 * @param countryCode
	 *            Código de pais
	 * @param cityCode
	 *            Código de ciudad
	 * @param eventName
	 *            Nombre del evento
	 * @param dateEvent
	 *            Fecha del evento YYYYMMDD
	 * @param establishmentId
	 *            Id del establecimiento (opcional)
	 * @param latitud
	 *            Latitud del evento
	 * @param longitud
	 *            Longitud del evento
	 * @param placeName
	 *            Nombre del lugar donde es el evento
	 * @param eventType
	 *            Tipo de evento
	 * @return Retorno de validación
	 */
	@POST
	@Path("/createEvent")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation createEvent(@FormParam("userId") String userId,
			@FormParam("countryCode") String countryCode,
			@FormParam("cityCode") String cityCode,
			@FormParam("eventName") String eventName,
			@FormParam("dateEvent") String dateEvent,
			@FormParam("timeEvent") String timeEvent,
			@FormParam("establishmentId") int establishmentId,
			@FormParam("latitud") double latitud,
			@FormParam("longitud") double longitud,
			@FormParam("placeName") String placeName,
			@FormParam("eventType") int eventType) {
		LogLogger.getInstance(getClass()).logger("Inicia createEvent",
				LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = eventServices.createEvent(userId,
				countryCode, cityCode, eventName,
				DateUtils.getDateDateYYYYMMDD(dateEvent),
				DateUtils.getTimeDateHHMMSS(timeEvent), establishmentId,
				latitud, longitud, placeName, eventType);
		LogLogger.getInstance(getClass()).logger("Finaliza createEvent",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Busca eventos en el sistema
	 * 
	 * @param userId
	 *            Id del usuario que realiza la busqueda
	 * @param clue
	 *            Pista de nombre del evento
	 * @param latitud
	 *            Latitud del evento
	 * @param longitud
	 *            Longitud del evento
	 * @param ratio
	 *            Radio de busqueda
	 * @return Lista de eventos
	 */
	@POST
	@Path("/findEvents")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Evmevent> findEvents(@FormParam("userId") String userId,
			@FormParam("clue") String clue,
			@FormParam("latitud") double latitud,
			@FormParam("longitud") double longitud,
			@FormParam("ratio") double ratio) {
		LogLogger.getInstance(getClass()).logger("Inicia findEvents",
				LogLogger.DEBUG);
		// Ejecuta servicio
		List<Evmevent> response = eventServices.findEvents(userId, clue,
				latitud, longitud, ratio);
		LogLogger.getInstance(getClass()).logger("Finaliza findEvents",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Registra participación de un usuario en un evento
	 * 
	 * @param countryCode
	 *            Código del pais
	 * @param cityCode
	 *            Código de la ciudad
	 * @param eventDate
	 *            Fecha del evento
	 * @param eventSec
	 *            Secuencia del evento
	 * @param userId
	 *            Id del usuario
	 * @param participationInd
	 *            Indicador de participación en el evento
	 * @return Retorno de validación
	 */
	@POST
	@Path("/registerUsersEventParticipation")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation registerUsersEventParticipation(
			@FormParam("countryCode") String countryCode,
			@FormParam("cityCode") String cityCode,
			@FormParam("eventDate") String eventDate,
			@FormParam("eventSec") int eventSec,
			@FormParam("userId") String userId,
			@FormParam("participationInd") String participationInd) {
		LogLogger.getInstance(getClass()).logger(
				"Inicia registerUsersEventParticipation", LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = eventServices
				.registerUsersEventParticipation(countryCode, cityCode,
						DateUtils.getDateDateYYYYMMDD(eventDate), eventSec,
						userId, participationInd);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza registerUsersEventParticipation", LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Busca los eventos creados por un usuario
	 * 
	 * @param userId
	 *            Id de usuario que ha creado el evento
	 * @return Lista de eventos del usuario
	 */
	@POST
	@Path("/findUserEvents")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Evmevent> findUserEvents(@FormParam("userId") String userId) {
		LogLogger.getInstance(getClass()).logger("Inicia findUserEvents",
				LogLogger.DEBUG);
		// Ejecuta servicio
		List<Evmevent> response = eventServices.findUserEvents(userId);
		LogLogger.getInstance(getClass()).logger("Finaliza findUserEvents",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Método para actualizar un evento
	 * 
	 * @param countryCode
	 *            Código de pais del evento
	 * @param cityCode
	 *            Código de ciudad del evento
	 * @param dateEvent
	 *            Fecha del evento
	 * @param eventSec
	 *            Secuencia del evento
	 * @param eventName
	 *            Nombre del evento
	 * @param establishmentId
	 *            Establecimiento del evento
	 * @param latitud
	 *            Latitud del evento
	 * @param longitud
	 *            Longitud del evento
	 * @param placeName
	 *            Nombre del lugar donde es el evento
	 * @param eventType
	 *            tipo de evento
	 * @return Retorno de validación
	 */
	@POST
	@Path("/updateEvent")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation updateEvent(
			@FormParam("countryCode") String countryCode,
			@FormParam("cityCode") String cityCode,
			@FormParam("dateEvent") String dateEvent,
			@FormParam("eventSec") int eventSec,
			@FormParam("timeEvent") String timeEvent,
			@FormParam("eventName") String eventName,
			@FormParam("establishmentId") int establishmentId,
			@FormParam("latitud") double latitud,
			@FormParam("longitud") double longitud,
			@FormParam("placeName") String placeName,
			@FormParam("eventType") int eventType,
			@FormParam("isActive") String isActive) {
		LogLogger.getInstance(getClass()).logger("Inicia updateEvent",
				LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = eventServices.updateEvent(countryCode,
				cityCode, DateUtils.getDateDateYYYYMMDD(dateEvent), eventSec,
				DateUtils.getTimeDateHHMMSS(timeEvent), eventName,
				establishmentId, latitud, longitud, placeName, eventType,
				isActive);
		LogLogger.getInstance(getClass()).logger("Finaliza updateEvent",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

}
