/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Evdimaev;
import com.vegadvisor.server.persistence.bo.EvdimaevId;
import com.vegadvisor.server.persistence.dao.IEvdimaevDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Evdimaev
 */
public class EvdimaevDAO extends GenericHbmDAO<Evdimaev, EvdimaevId> implements
		IEvdimaevDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EvdimaevDAO() {
		super(Evdimaev.class);
	}
}
