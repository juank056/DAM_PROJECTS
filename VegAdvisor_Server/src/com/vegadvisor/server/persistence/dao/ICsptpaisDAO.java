/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Csptpais;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface ICsptpaisDAO extends GenericDAO<Csptpais, String> {

	/**
	 * Busca pais por nombre like
	 * 
	 * @param paidpaiaf
	 *            Nombre del pais
	 * @return Lista de paises con nombre like
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Csptpais> findByName(String paidpaiaf) throws DAOException;

}
