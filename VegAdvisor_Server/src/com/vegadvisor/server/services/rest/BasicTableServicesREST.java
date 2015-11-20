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

import com.vegadvisor.server.persistence.bo.Cspciuda;
import com.vegadvisor.server.persistence.bo.Csptiest;
import com.vegadvisor.server.persistence.bo.Csptieve;
import com.vegadvisor.server.persistence.bo.Csptpais;
import com.vegadvisor.server.services.IBasicTablesServices;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios Rest de tablas básicas
 * 
 * @author JuanCamilo
 *
 */
@Path("/basic")
public class BasicTableServicesREST {

	/**
	 * Servicios de tablas básicas
	 */
	private IBasicTablesServices basicTablesServices;

	/**
	 * Constructor
	 */
	public BasicTableServicesREST() {
		// Inicia servicios
		basicTablesServices = SpringAppContext.getAppContext().getBean(
				IBasicTablesServices.class);
	}

	/**
	 * Busca paises por nombre
	 * 
	 * @param clue
	 *            Pista del nombre de pais
	 * @return Lista de paises que cumplen con criterio
	 */
	@POST
	@Path("/getCountries")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Csptpais> getCountries(
			@FormParam("countryCode") String countryCode,
			@FormParam("clue") String clue) {
		LogLogger.getInstance(getClass()).logger("Inicia getCountries",
				LogLogger.DEBUG);
		// Ejecuta servicio
		List<Csptpais> response = basicTablesServices.getCountries(countryCode,
				clue);
		LogLogger.getInstance(getClass()).logger("Finaliza getCountries",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Busca ciudades por nombre
	 * 
	 * @param countryCode
	 *            Pais de la ciudad
	 * @param clue
	 *            Pista del nombre de la ciudad
	 * @return Lista de ciudades
	 */
	@POST
	@Path("/getCities")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cspciuda> getCities(
			@FormParam("countryCode") String countryCode,
			@FormParam("cityCode") String cityCode,
			@FormParam("clue") String clue) {
		LogLogger.getInstance(getClass()).logger("Inicia getCities",
				LogLogger.DEBUG);
		// Ejecuta servicio
		List<Cspciuda> response = basicTablesServices.getCities(countryCode,
				cityCode, clue);
		LogLogger.getInstance(getClass()).logger("Finaliza getCities",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Busca tipos de establecimiento de acuerdo a nombre
	 * 
	 * @param clue
	 *            pista del nombre de tipo establecimiento
	 * @return Lista de tipos de establecimiento
	 */
	@POST
	@Path("/getEstablishmentTypes")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Csptiest> getEstablishmentTypes(
			@FormParam("establishmentTypeId") int establishmentTypeId,
			@FormParam("clue") String clue) {
		LogLogger.getInstance(getClass()).logger(
				"Inicia getEstablishmentTypes", LogLogger.DEBUG);
		// Ejecuta servicio
		List<Csptiest> response = basicTablesServices.getEstablishmentTypes(
				establishmentTypeId, clue);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza getEstablishmentTypes", LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Busca tipos de evento segun nombre
	 * 
	 * @param clue
	 *            Pista del nombre del tipo de evento
	 * @return Lista de tipos de evento
	 */
	@POST
	@Path("/getEventTypes")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public List<Csptieve> getEventTypes(
			@FormParam("eventTypeId") int eventTypeId,
			@FormParam("clue") String clue) {
		LogLogger.getInstance(getClass()).logger("Inicia getEventTypes",
				LogLogger.DEBUG);
		// Ejecuta servicio
		List<Csptieve> response = basicTablesServices.getEventTypes(
				eventTypeId, clue);
		LogLogger.getInstance(getClass()).logger("Finaliza getEventTypes",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}
}
