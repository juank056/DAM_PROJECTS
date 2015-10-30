/**
 * 
 */
package com.vegadvisor.server.services;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.bo.Esmestab;
import com.vegadvisor.server.services.bo.EstablishmentStatistic;

/**
 * Interfaz que define los servicios relacionados con los establecimientos
 * 
 * @author JuanCamilo
 *
 */
public interface IEstablishmentServices {

	/**
	 * Método para buscar establecimientos del sistema
	 * 
	 * @param clue
	 *            Pista de establecimientos (para like)
	 * @param ratio
	 *            Radio de busqueda de establecimientos
	 * @param latitud
	 *            latitud actual de un usuario
	 * @param longitud
	 *            longitud actual de un usuario
	 * @return Lista de establecimientos que cumplen el criterio de búsqueda
	 */
	public List<Esmestab> findEstablishments(String clue, double ratio,
			double latitud, double longitud);

	/**
	 * Método para crear o actualizar establecimientos en el sistema
	 * 
	 * @param establishmentId
	 *            Id del establecimiento (cero es para creación)
	 * @param companyName
	 *            Nombre del establecimiento
	 * @param establishmentType
	 *            Tipo de establecimiento
	 * @param address
	 *            Dirección del establecimiento
	 * @param phones
	 *            Teléfonos del establecimiento
	 * @param country
	 *            Código de pais
	 * @param city
	 *            Código de ciudad
	 * @param latitud
	 *            Latitud del establecimiento
	 * @param longitud
	 *            Longitud del establecimiento
	 * @param description
	 *            Descripción del establecimiento
	 * @param active
	 *            Indicador de activo
	 * @return Indicador de creación/actualización y mensaje
	 */
	public String[] createOrUpdateEstablishment(int establishmentId,
			String companyName, int establishmentType, String address,
			String phones, String country, String city, double latitud,
			double longitud, String description, String active);

	/**
	 * Obtiene lista de los establecimientos de un usuario
	 * 
	 * @param userId
	 *            Id del usuario
	 * @return Lista de los establecimientos creados por un usuario
	 */
	public List<Esmestab> getUserEstablishments(String userId);

	/**
	 * Obtiene estadisticas de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param sinceDate
	 *            Fecha desde de consulta
	 * @param untilDate
	 *            Fecha hasta de consulta
	 * @return Lista de estadisticas por día para el establecimiento
	 */
	public List<EstablishmentStatistic> getEstablishmentStatistics(
			int establishmentId, Date sinceDate, Date untilDate);

}
