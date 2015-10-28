/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Usmusuar;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IUsmusuarDAO extends GenericDAO<Usmusuar, String> {

	/**
	 * Busca un usuario por email
	 * 
	 * @param usuemaiaf
	 *            Email del usuario
	 * @return Usuario con el id registrado
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Usmusuar> findByEmail(String usuemaiaf) throws DAOException;

}
