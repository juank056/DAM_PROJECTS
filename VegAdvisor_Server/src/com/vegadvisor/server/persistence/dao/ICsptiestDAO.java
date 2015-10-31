/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Csptiest;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface ICsptiestDAO extends GenericDAO<Csptiest, Integer> {

	/**
	 * Busca tipo establecimiento por nombre like
	 * 
	 * @param tesntesaf
	 *            Nombre tipo establecimiento
	 * @return Lista de tipos de establecimiento con nombre like
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Csptiest> findByName(String tesntesaf) throws DAOException;
}
