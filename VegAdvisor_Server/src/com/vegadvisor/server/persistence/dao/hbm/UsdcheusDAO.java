/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Usdcheus;
import com.vegadvisor.server.persistence.bo.UsdcheusId;
import com.vegadvisor.server.persistence.dao.IUsdcheusDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Usdcheus
 */
public class UsdcheusDAO extends GenericHbmDAO<Usdcheus, UsdcheusId> implements
		IUsdcheusDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public UsdcheusDAO() {
		super(Usdcheus.class);
	}
}
