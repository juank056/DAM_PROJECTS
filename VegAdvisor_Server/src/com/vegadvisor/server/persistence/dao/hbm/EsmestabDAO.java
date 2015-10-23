/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Esmestab;
import com.vegadvisor.server.persistence.dao.IEsmestabDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Esmestab
 */
public class EsmestabDAO extends GenericHbmDAO<Esmestab, Integer> implements
		IEsmestabDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EsmestabDAO() {
		super(Esmestab.class);
	}
}
