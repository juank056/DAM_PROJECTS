/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Fodreshi;
import com.vegadvisor.server.persistence.bo.FodreshiId;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IFodreshiDAO extends GenericDAO<Fodreshi, FodreshiId> {

	/**
	 * Busca respuestas de hilo de acuerdo a su llave padre
	 * 
	 * @param hifchifnk
	 *            llave padre del hilo
	 * @return Lista de respuestas registradas para el hilo
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Fodreshi> findByParent(Integer hifchifnk) throws DAOException;

}
