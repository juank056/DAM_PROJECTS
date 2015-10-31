/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Csptiest;
import com.vegadvisor.server.persistence.dao.ICsptiestDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.utils.Constants;

/**
 * @author VegAdvisor DAO para manejar la tabla de Csptiest
 */
public class CsptiestDAO extends GenericHbmDAO<Csptiest, Integer> implements
		ICsptiestDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public CsptiestDAO() {
		super(Csptiest.class);
	}

	/**
	 * Busca tipo establecimiento por nombre like
	 * 
	 * @param tesntesaf
	 *            Nombre tipo establecimiento
	 * @return Lista de tipos de establecimiento con nombre like
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Csptiest> findByName(String tesntesaf) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.like("tesntesaf", Constants.PERCENTAGE
				+ tesntesaf.toUpperCase() + Constants.PERCENTAGE));
		return this.findByCriteria(crit);
	}
}
