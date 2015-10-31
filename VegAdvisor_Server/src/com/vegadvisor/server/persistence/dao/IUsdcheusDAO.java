/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Usdcheus;
import com.vegadvisor.server.persistence.bo.UsdcheusId;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IUsdcheusDAO extends GenericDAO<Usdcheus, UsdcheusId> {

	/**
	 * Busca checkins de usuarios en un establecmiento para un rango de fechas
	 * 
	 * @param estcestnk
	 *            Id del establecimiento a buscar
	 * @param sinceDate
	 *            fecha desde de la busqueda
	 * @param untilDate
	 *            fecha hasta de la busqueda
	 * @return Lista de checkins de usuarios en el rango establecido
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Usdcheus> findByRange(int estcestnk, Date sinceDate,
			Date untilDate) throws DAOException;

}
