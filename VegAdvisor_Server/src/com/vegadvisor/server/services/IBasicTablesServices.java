/**
 * 
 */
package com.vegadvisor.server.services;

import com.vegadvisor.server.persistence.bo.Cspciuda;
import com.vegadvisor.server.persistence.bo.Csptiest;
import com.vegadvisor.server.persistence.bo.Csptieve;
import com.vegadvisor.server.persistence.bo.Csptpais;

import java.util.List;

/**
 * Servicios relacionados con las tablas básicas del sistema
 * 
 * @author JuanCamilo
 *
 */
public interface IBasicTablesServices {

	/**
	 * Obtiene lista de paises de acuerdo a una pista del nombre
	 * 
	 * @param countryCode
	 *            Código de pais
	 * @param clue
	 *            Pista del nombre del pais
	 * @return Lista de paises que cumplen con el criterio
	 */
	public List<Csptpais> getCountries(String countryCode, String clue);

	/**
	 * Obtiene ciudades de acuerdo a un pais y una pista del nombre
	 * 
	 * @param countryCode
	 *            Código del pais
	 * @param cityCode
	 *            Código de Ciudad
	 * @param clue
	 *            Pista del nombre de la ciudad
	 * @return Lista de ciudades que cumplen con las condiciones
	 */
	public List<Cspciuda> getCities(String countryCode, String cityCode,
			String clue);

	/**
	 * Obtiene lista de tipos de establecimiento de acuerdo a una pista
	 * 
	 * @param establishmentTypeId
	 *            Id del tipo de establecimiento
	 * @param clue
	 *            Pista del nombre de tipo establecimiento
	 * @return Lista de tipos de establecimiento
	 */
	public List<Csptiest> getEstablishmentTypes(int establishmentTypeId,
			String clue);

	/**
	 * Obtiene lista de tipos de eventos de acuerdo a una pista
	 * 
	 * @param eventTypeId
	 *            Id del tipo de evento
	 * @param clue
	 *            Pista del nombre de tipo de evento
	 * @return Lista de tipos de eventos
	 */
	public List<Csptieve> getEventTypes(int eventTypeId, String clue);

}
