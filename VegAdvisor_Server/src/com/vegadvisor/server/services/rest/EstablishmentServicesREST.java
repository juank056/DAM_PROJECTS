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

import com.vegadvisor.server.persistence.bo.Esmestab;
import com.vegadvisor.server.services.IEstablishmentServices;
import com.vegadvisor.server.services.bo.EstablishmentStatistic;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios Rest de Establecimientos
 * 
 * @author JuanCamilo
 *
 */
@Path("/establishment")
public class EstablishmentServicesREST {

	/**
	 * Servicios de Establecimientos
	 */
	private IEstablishmentServices establishmentServices;

	/**
	 * Constructor
	 */
	public EstablishmentServicesREST() {
		// Inicia servicios
		this.establishmentServices = SpringAppContext.getAppContext().getBean(
				IEstablishmentServices.class);
	}

	/**
	 * Crea o actualiza un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param userId
	 *            Id del usuario que lo crea
	 * @param companyName
	 *            Nombre del establecimiento
	 * @param establishmentType
	 *            Tipo de establecimiento
	 * @param address
	 *            Dirección del establecimiento
	 * @param phones
	 *            Teléfonos del establecimiento
	 * @param openingTime
	 *            Hora de apertura del establecimiento
	 * @param closingTime
	 *            Hora de cierre del establecimiento
	 * @param country
	 *            Pais del establecimiento
	 * @param city
	 *            Ciudad del establecimiento
	 * @param latitud
	 *            Latitud del establecimiento
	 * @param longitud
	 *            Longitud del establecimiento
	 * @param description
	 *            Descripción del establecimiento
	 * @param active
	 *            Indicador de establecimiento activo
	 * @return
	 */
	@POST
	@Path("/createOrUpdateEstablishment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation createOrUpdateEstablishment(
			@FormParam("establishmentId") int establishmentId,
			@FormParam("userId") String userId,
			@FormParam("companyName") String companyName,
			@FormParam("establishmentType") int establishmentType,
			@FormParam("address") String address,
			@FormParam("phones") String phones,
			@FormParam("openingTime") String openingTime,
			@FormParam("closingTime") String closingTime,
			@FormParam("country") String country,
			@FormParam("city") String city,
			@FormParam("latitud") double latitud,
			@FormParam("longitud") double longitud,
			@FormParam("description") String description,
			@FormParam("active") String active) {
		LogLogger.getInstance(getClass()).logger(
				"Inicia createOrUpdateEstablishment", LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = establishmentServices
				.createOrUpdateEstablishment(establishmentId, userId,
						companyName, establishmentType, address, phones,
						DateUtils.getTimeDateHHMMSS(openingTime),
						DateUtils.getTimeDateHHMMSS(closingTime), country,
						city, latitud, longitud, description, active);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza createOrUpdateEstablishment", LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Busca establecimientos
	 * 
	 * @param clue
	 *            Pista de nombre del establecimiento
	 * @param ratio
	 *            Radio de busqueda del establecimiento
	 * @param latitud
	 *            latitud actual
	 * @param longitud
	 *            longitud actual
	 * @return Lista de establecimientos encontrados
	 */
	@POST
	@Path("/findEstablishments")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Esmestab> findEstablishments(@FormParam("clue") String clue,
			@FormParam("ratio") double ratio,
			@FormParam("latitud") double latitud,
			@FormParam("longitud") double longitud) {
		LogLogger.getInstance(getClass()).logger("Inicia findEstablishments",
				LogLogger.DEBUG);
		// Ejecuta servicio
		List<Esmestab> response = establishmentServices.findEstablishments(
				clue, ratio, latitud, longitud);
		LogLogger.getInstance(getClass()).logger("Finaliza findEstablishments",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Obtiene estadisticas de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param sinceDate
	 *            Fecha desde de busqueda
	 * @param untilDate
	 *            Fecha hasta de busqueda
	 * @return Lista de estadisticas del establecimiento
	 */
	@POST
	@Path("/getEstablishmentStatistics")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<EstablishmentStatistic> getEstablishmentStatistics(
			@FormParam("establishmentId") int establishmentId,
			@FormParam("sinceDate") String sinceDate,
			@FormParam("untilDate") String untilDate) {
		LogLogger.getInstance(getClass()).logger(
				"Inicia getEstablishmentStatistics", LogLogger.DEBUG);
		// Ejecuta servicio
		List<EstablishmentStatistic> response = establishmentServices
				.getEstablishmentStatistics(establishmentId,
						DateUtils.getDateDateYYYYMMDD(sinceDate),
						DateUtils.getDateDateYYYYMMDD(untilDate));
		LogLogger.getInstance(getClass()).logger(
				"Finaliza getEstablishmentStatistics", LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Obtiene los establecimientos de un usuario
	 * 
	 * @param userId
	 *            Id del usuario
	 * @return Lista de establecimientos del usuario
	 */
	@POST
	@Path("/getUserEstablishments")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Esmestab> getUserEstablishments(
			@FormParam("userId") String userId) {
		LogLogger.getInstance(getClass()).logger(
				"Inicia getUserEstablishments", LogLogger.DEBUG);
		// Ejecuta servicio
		List<Esmestab> response = establishmentServices
				.getUserEstablishments(userId);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza getUserEstablishments", LogLogger.DEBUG);
		// Retornamos
		return response;
	}
}
