/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Evmevent;
import com.vegadvisor.server.persistence.bo.EvmeventId;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IEvmeventDAO extends GenericDAO<Evmevent, EvmeventId> {

	/**
	 * Busca eventos por nombre y por cercania de localización
	 * 
	 * @param evedeveaf
	 *            Nombre del evento de pista
	 * @param latitud
	 *            Latitud de referencia
	 * @param longitud
	 *            Longitud de referencia
	 * @param ratio
	 *            Radio de busqueda
	 * @return Lista de eventos que cumplen con las condiciones
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Evmevent> findByNameAndPosition(String evedeveaf,
			double latitud, double longitud, double ratio) throws DAOException;

	/**
	 * Busca los eventos de un usuario
	 * 
	 * @param usucusuak
	 *            Id del usuario
	 * @return Lista de eventos del usuario
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Evmevent> findUserEvents(String usucusuak) throws DAOException;

}
