/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Csptieve;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface ICsptieveDAO extends GenericDAO<Csptieve, Integer> {

	/**
	 * Busca tipo evento por nombre like
	 * 
	 * @param tevntevaf
	 *            Nombre tipo de evento
	 * @return Lista de tipos de evento con nombre like
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Csptieve> findByName(String tevntevaf) throws DAOException;
}
