/**
 * 
 */
package com.vegadvisor.server.persistence.dao;

import java.util.List;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Chdmensa;
import com.vegadvisor.server.persistence.bo.ChdmensaId;

/**
 * DAO para trabajar con la tabla Aucnegin
 * 
 * @author VegAdvisor Team
 *
 */
public interface IChdmensaDAO extends GenericDAO<Chdmensa, ChdmensaId> {

	/**
	 * Busca los mensajes pendientes de entrega para un usuario
	 * 
	 * @param usucusdak
	 *            Usuario que debe recibir los mensajes
	 * @return Lista de mensajes pendientes de recepcion para el usuario
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Chdmensa> findUserPendingMessages(String usucusdak)
			throws DAOException;

}
