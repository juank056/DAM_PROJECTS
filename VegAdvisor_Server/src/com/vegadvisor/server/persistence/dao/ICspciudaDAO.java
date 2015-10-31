/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Cspciuda;
import com.vegadvisor.server.persistence.bo.CspciudaId;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface ICspciudaDAO extends GenericDAO<Cspciuda, CspciudaId> {

	/**
	 * Busca ciudad por nombre like
	 * 
	 * @param paicpaiak
	 *            Código de pais
	 * @param ciunciuaf
	 *            Nombre de la ciudad
	 * @return Lista de ciudades con nombre like
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Cspciuda> findByName(String paicpaiak, String ciunciuaf)
			throws DAOException;

}
