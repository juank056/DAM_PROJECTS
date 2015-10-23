/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Evdevvus;
import com.vegadvisor.server.persistence.bo.EvdevvusId;
import com.vegadvisor.server.persistence.dao.IEvdevvusDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Evdevvus
 */
public class EvdevvusDAO extends GenericHbmDAO<Evdevvus, EvdevvusId> implements
		IEvdevvusDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EvdevvusDAO() {
		super(Evdevvus.class);
	}
}
