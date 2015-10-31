/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Esmestab;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IEsmestabDAO extends GenericDAO<Esmestab, Integer> {

	/**
	 * Busca establecimientos cercanos a una posicion y que cumplan con un
	 * nombre
	 * 
	 * @param estnestaf
	 *            nombre del establecimiento
	 * @param ratio
	 *            radio de busqueda en kilometros
	 * @param latitud
	 *            latitud del punto de busqueda
	 * @param longitud
	 *            longitud del punto de busqueda
	 * @return Lista de establecimientos que cumplen con el nombre y que estan
	 *         dentro del rango de busqueda
	 * @throws DAOException
	 *             error en la base de datos
	 */
	public List<Esmestab> findByNameAndPosition(String estnestaf, double ratio,
			double latitud, double longitud) throws DAOException;

	/**
	 * Busca los establecimientos de un usuario
	 * 
	 * @param usucusuak
	 *            Codigo de usuario
	 * @return Establecimientos creados por un usuario
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Esmestab> findByUser(String usucusuak) throws DAOException;

}
