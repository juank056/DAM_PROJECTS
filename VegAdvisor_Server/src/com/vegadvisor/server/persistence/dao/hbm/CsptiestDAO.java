/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Csptiest;
import com.vegadvisor.server.persistence.dao.ICsptiestDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Csptiest
 */
public class CsptiestDAO extends GenericHbmDAO<Csptiest, Integer> implements
		ICsptiestDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public CsptiestDAO() {
		super(Csptiest.class);
	}
}
