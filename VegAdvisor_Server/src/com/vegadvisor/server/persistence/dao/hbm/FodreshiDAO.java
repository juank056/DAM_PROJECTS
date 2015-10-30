/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Fodreshi;
import com.vegadvisor.server.persistence.bo.FodreshiId;
import com.vegadvisor.server.persistence.dao.IFodreshiDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;

/**
 * @author VegAdvisor DAO para manejar la tabla de Fodreshi
 */
public class FodreshiDAO extends GenericHbmDAO<Fodreshi, FodreshiId> implements
		IFodreshiDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public FodreshiDAO() {
		super(Fodreshi.class);
	}

	/**
	 * Busca respuestas de hilo de acuerdo a su llave padre
	 * 
	 * @param hifchifnk
	 *            llave padre del hilo
	 * @return Lista de respuestas registradas para el hilo
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Fodreshi> findByParent(Integer hifchifnk) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("id.hifchifnk", hifchifnk));
		return this.findByCriteria(crit);
	}
}
