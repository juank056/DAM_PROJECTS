/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Usmusuar;
import com.vegadvisor.server.persistence.dao.IUsmusuarDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Usmusuar
 */
public class UsmusuarDAO extends GenericHbmDAO<Usmusuar, String> implements
		IUsmusuarDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public UsmusuarDAO() {
		super(Usmusuar.class);
	}

	/**
	 * Busca un usuario por email
	 * 
	 * @param usuemaiaf
	 *            Email del usuario
	 * @return Usuario con el id registrado
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Usmusuar> findByEmail(String usuemaiaf) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("usuemaiaf", usuemaiaf));
		return this.findByCriteria(crit);
	}
}
