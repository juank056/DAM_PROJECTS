/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Fomhilfo;
import com.vegadvisor.server.persistence.dao.IFomhilfoDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.utils.Constants;

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

	/**
	 * Busca hilos de foro de acuerdo a un titulo
	 * 
	 * @param hiftituaf
	 *            Titulo del foro
	 * @return Hilos de foro con el titulo de entrada
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Fomhilfo> findByName(String hiftituaf) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.like("hiftituaf", Constants.PERCENTAGE
				+ hiftituaf.toUpperCase() + Constants.PERCENTAGE));
		return this.findByCriteria(crit, Order.desc("hiffregff"));
	}
}
