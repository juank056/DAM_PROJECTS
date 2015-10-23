/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Chdmensa;
import com.vegadvisor.server.persistence.bo.ChdmensaId;
import com.vegadvisor.server.persistence.dao.IChdmensaDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Chdmensa
 */
public class ChdmensaDAO extends GenericHbmDAO<Chdmensa, ChdmensaId> implements
		IChdmensaDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public ChdmensaDAO() {
		super(Chdmensa.class);
	}
}
