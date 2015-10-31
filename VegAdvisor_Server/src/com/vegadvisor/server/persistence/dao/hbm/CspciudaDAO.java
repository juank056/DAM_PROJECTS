/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Cspciuda;
import com.vegadvisor.server.persistence.bo.CspciudaId;
import com.vegadvisor.server.persistence.dao.ICspciudaDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.utils.Constants;

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

	/**
	 * Busca ciudad por nombre like
	 * 
	 * @param paicpaiak
	 *            Código de pais
	 * @param ciunciuaf
	 *            Nombre de la ciudad
	 * @return Lista de ciudades con nombre like
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Cspciuda> findByName(String paicpaiak, String ciunciuaf)
			throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("id.paicpaiak", paicpaiak));
		crit.add(Restrictions.like("ciunciuaf", Constants.PERCENTAGE
				+ ciunciuaf.toUpperCase() + Constants.PERCENTAGE));
		return this.findByCriteria(crit);
	}
}
