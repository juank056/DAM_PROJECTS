/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Csptpais;
import com.vegadvisor.server.persistence.dao.ICsptpaisDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.utils.Constants;

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

	/**
	 * Busca pais por nombre like
	 * 
	 * @param paidpaiaf
	 *            Nombre del pais
	 * @return Lista de paises con nombre like
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Csptpais> findByName(String paidpaiaf) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.like("paidpaiaf", Constants.PERCENTAGE
				+ paidpaiaf.toUpperCase() + Constants.PERCENTAGE));
		return this.findByCriteria(crit);
	}
}
