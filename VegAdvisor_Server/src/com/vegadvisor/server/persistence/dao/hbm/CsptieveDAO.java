/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Csptieve;
import com.vegadvisor.server.persistence.dao.ICsptieveDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.utils.Constants;

/**
 * @author VegAdvisor DAO para manejar la tabla de Csptieve
 */
public class CsptieveDAO extends GenericHbmDAO<Csptieve, Integer> implements
		ICsptieveDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public CsptieveDAO() {
		super(Csptieve.class);
	}

	/**
	 * Busca tipo evento por nombre like
	 * 
	 * @param tevntevaf
	 *            Nombre tipo de evento
	 * @return Lista de tipos de evento con nombre like
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Csptieve> findByName(String tevntevaf) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.like("tevntevaf", Constants.PERCENTAGE
				+ tevntevaf.toUpperCase() + Constants.PERCENTAGE));
		return this.findByCriteria(crit);
	}
}
