/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Esdimaes;
import com.vegadvisor.server.persistence.bo.EsdimaesId;
import com.vegadvisor.server.persistence.dao.IEsdimaesDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Esdimaes
 */
public class EsdimaesDAO extends GenericHbmDAO<Esdimaes, EsdimaesId> implements
		IEsdimaesDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EsdimaesDAO() {
		super(Esdimaes.class);
	}
}
