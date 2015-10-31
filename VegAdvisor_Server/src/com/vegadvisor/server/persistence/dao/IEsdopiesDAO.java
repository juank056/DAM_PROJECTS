/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Esdopies;
import com.vegadvisor.server.persistence.bo.EsdopiesId;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IEsdopiesDAO extends GenericDAO<Esdopies, EsdopiesId> {

	/**
	 * Busca opiniones de usuarios en un establecmiento para un rango de fechas
	 * 
	 * @param estcestnk
	 *            Id del establecimiento a buscar
	 * @param sinceDate
	 *            fecha desde de la busqueda
	 * @param untilDate
	 *            fecha hasta de la busqueda
	 * @return Lista de opiniones de usuarios en el rango establecido
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Esdopies> findByRange(int estcestnk, Date sinceDate,
			Date untilDate) throws DAOException;

	/**
	 * Busca los registros de opiniones para un establecimiento
	 * 
	 * @param estcestnk
	 *            Codigo del establecimiento
	 * @param maxOpinions
	 *            Maximo de opiniones a traer
	 * @return Lista de opiniones del establecimiento
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Esdopies> findByEstablishment(int estcestnk, int maxOpinions)
			throws DAOException;

}
