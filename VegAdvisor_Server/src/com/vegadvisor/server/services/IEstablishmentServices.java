/**
 * 
 */
package com.vegadvisor.server.services;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.bo.Esmestab;
import com.vegadvisor.server.services.bo.EstablishmentStatistic;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * Interfaz que define los servicios relacionados con los establecimientos
 * 
 * @author JuanCamilo
 *
 */
public interface IEstablishmentServices {

	/**
	 * M�todo para buscar establecimientos del sistema
	 * 
	 * @param clue
	 *            Pista de establecimientos (para like)
	 * @param ratio
	 *            Radio de busqueda de establecimientos
	 * @param latitud
	 *            latitud actual de un usuario
	 * @param longitud
	 *            longitud actual de un usuario
	 * @return Lista de establecimientos que cumplen el criterio de b�squeda
	 */
	public List<Esmestab> findEstablishments(String clue, double ratio,
			double latitud, double longitud);

	/**
	 * M�todo para crear o actualizar establecimientos en el sistema
	 * 
	 * @param establishmentId
	 *            Id del establecimiento (cero es para creaci�n)
	 * @param userId
	 *            Usuario que crea el establecimiento
	 * @param companyName
	 *            Nombre del establecimiento
	 * @param establishmentType
	 *            Tipo de establecimiento
	 * @param address
	 *            Direcci�n del establecimiento
	 * @param phones
	 *            Tel�fonos del establecimiento
	 * @param openingTime
	 *            Horario de apertura
	 * @param closingTime
	 *            Horario de cierre
	 * @param country
	 *            C�digo de pais
	 * @param city
	 *            C�digo de ciudad
	 * @param latitud
	 *            Latitud del establecimiento
	 * @param longitud
	 *            Longitud del establecimiento
	 * @param description
	 *            Descripci�n del establecimiento
	 * @param active
	 *            Indicador de activo
	 * @return Indicador de creaci�n/actualizaci�n y mensaje
	 */
	public ReturnValidation createOrUpdateEstablishment(int establishmentId,
			String userId, String companyName, int establishmentType,
			String address, String phones, Date openingTime, Date closingTime,
			String country, String city, double latitud, double longitud,
			String description, String active);

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
	 * @return Lista de estadisticas por d�a para el establecimiento
	 */
	public List<EstablishmentStatistic> getEstablishmentStatistics(
			int establishmentId, Date sinceDate, Date untilDate);

}
