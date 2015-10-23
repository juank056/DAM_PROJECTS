/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Esdopies;
import com.vegadvisor.server.persistence.bo.EsdopiesId;
import com.vegadvisor.server.persistence.dao.IEsdopiesDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Esdopies
 */
public class EsdopiesDAO extends GenericHbmDAO<Esdopies, EsdopiesId> implements
		IEsdopiesDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EsdopiesDAO() {
		super(Esdopies.class);
	}
}
