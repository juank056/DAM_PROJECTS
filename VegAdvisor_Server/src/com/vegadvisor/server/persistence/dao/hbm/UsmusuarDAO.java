/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Usmusuar;
import com.vegadvisor.server.persistence.dao.IUsmusuarDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Usmusuar
 */
public class UsmusuarDAO extends GenericHbmDAO<Usmusuar, String> implements
		IUsmusuarDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public UsmusuarDAO() {
		super(Usmusuar.class);
	}
}
