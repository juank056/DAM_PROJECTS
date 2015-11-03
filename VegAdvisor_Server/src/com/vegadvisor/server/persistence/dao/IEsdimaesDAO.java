/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Esdimaes;
import com.vegadvisor.server.persistence.bo.EsdimaesId;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IEsdimaesDAO extends GenericDAO<Esdimaes, EsdimaesId> {

	/**
	 * Carga las imagenes de un establecimiento
	 * 
	 * @param estcestnk
	 *            Codigo del establecimiento
	 * @return Lista de imagenes del establecimiento
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Esdimaes> findByEstablishment(Integer estcestnk)
			throws DAOException;

}
