/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Fomhilfo;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IFomhilfoDAO extends GenericDAO<Fomhilfo, Integer> {

	/**
	 * Busca hilos de foro de acuerdo a un titulo
	 * 
	 * @param hiftituaf
	 *            Titulo del foro
	 * @return Hilos de foro con el titulo de entrada
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Fomhilfo> findByName(String hiftituaf) throws DAOException;

}
