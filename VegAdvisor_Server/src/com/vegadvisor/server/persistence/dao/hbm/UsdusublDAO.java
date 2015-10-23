/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Usdusubl;
import com.vegadvisor.server.persistence.bo.UsdusublId;
import com.vegadvisor.server.persistence.dao.IUsdusublDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Usdusubl
 */
public class UsdusublDAO extends GenericHbmDAO<Usdusubl, UsdusublId> implements
		IUsdusublDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public UsdusublDAO() {
		super(Usdusubl.class);
	}
}
