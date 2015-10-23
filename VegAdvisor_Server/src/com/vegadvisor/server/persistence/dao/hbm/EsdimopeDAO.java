/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Esdimope;
import com.vegadvisor.server.persistence.bo.EsdimopeId;
import com.vegadvisor.server.persistence.dao.IEsdimopeDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Esdimope
 */
public class EsdimopeDAO extends GenericHbmDAO<Esdimope, EsdimopeId> implements
		IEsdimopeDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EsdimopeDAO() {
		super(Esdimope.class);
	}
}
