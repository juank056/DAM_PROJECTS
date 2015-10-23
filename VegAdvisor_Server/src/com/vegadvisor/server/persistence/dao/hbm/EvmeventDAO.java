/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Evmevent;
import com.vegadvisor.server.persistence.bo.EvmeventId;
import com.vegadvisor.server.persistence.dao.IEvmeventDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Evmevent
 */
public class EvmeventDAO extends GenericHbmDAO<Evmevent, EvmeventId> implements
		IEvmeventDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EvmeventDAO() {
		super(Evmevent.class);
	}
}
