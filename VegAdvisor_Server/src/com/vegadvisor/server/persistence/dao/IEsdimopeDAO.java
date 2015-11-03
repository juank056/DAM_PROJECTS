/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Esdimope;
import com.vegadvisor.server.persistence.bo.EsdimopeId;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IEsdimopeDAO extends GenericDAO<Esdimope, EsdimopeId> {

	/**
	 * Obtiene las imagenes de una opinion
	 * 
	 * @param estcestnk
	 *            Id del establecimiento
	 * @param oesfregfk
	 *            Fecha de registro de la opinion
	 * @param oescoesnk
	 *            Consecutivo de la opinión
	 * @return Lista de imagenes de la opinión
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Esdimope> findByOpinion(int estcestnk, Date oesfregfk,
			int oescoesnk) throws DAOException;

}
