/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import com.vegadvisor.server.persistence.bo.Fomhilfo;
import com.vegadvisor.server.persistence.dao.IFomhilfoDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Fomhilfo
 */
public class FomhilfoDAO extends GenericHbmDAO<Fomhilfo, Integer> implements
		IFomhilfoDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public FomhilfoDAO() {
		super(Fomhilfo.class);
	}
}
