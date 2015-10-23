/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Csptieve;
import com.vegadvisor.server.persistence.dao.ICsptieveDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Csptieve
 */
public class CsptieveDAO extends GenericHbmDAO<Csptieve, Integer> implements
		ICsptieveDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public CsptieveDAO() {
		super(Csptieve.class);
	}
}
