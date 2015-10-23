/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Csptpais;
import com.vegadvisor.server.persistence.dao.ICsptpaisDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Csptpais
 */
public class CsptpaisDAO extends GenericHbmDAO<Csptpais, String> implements
		ICsptpaisDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public CsptpaisDAO() {
		super(Csptpais.class);
	}
}
