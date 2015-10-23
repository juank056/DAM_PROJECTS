/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Fodreshi;
import com.vegadvisor.server.persistence.bo.FodreshiId;
import com.vegadvisor.server.persistence.dao.IFodreshiDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Fodreshi
 */
public class FodreshiDAO extends GenericHbmDAO<Fodreshi, FodreshiId> implements
		IFodreshiDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public FodreshiDAO() {
		super(Fodreshi.class);
	}
}
