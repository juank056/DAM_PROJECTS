/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Cspciuda;
import com.vegadvisor.server.persistence.bo.CspciudaId;
import com.vegadvisor.server.persistence.dao.ICspciudaDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Cspciuda
 */
public class CspciudaDAO extends GenericHbmDAO<Cspciuda, CspciudaId> implements
		ICspciudaDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public CspciudaDAO() {
		super(Cspciuda.class);
	}
}
