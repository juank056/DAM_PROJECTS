/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Evdimaev;
import com.vegadvisor.server.persistence.bo.EvdimaevId;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IEvdimaevDAO extends GenericDAO<Evdimaev, EvdimaevId> {

	/**
	 * Obiene imagenes de un evento
	 * 
	 * @param paicpaiak
	 *            Pais del evento
	 * @param ciucciuak
	 *            Ciudad del evento
	 * @param evefevefk
	 *            Fecha del evento
	 * @param evecevenk
	 *            Consecutivo del evento
	 * @return Lista de imagenes del evento
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Evdimaev> findByEvent(String paicpaiak, String ciucciuak,
			Date evefevefk, int evecevenk) throws DAOException;

}
