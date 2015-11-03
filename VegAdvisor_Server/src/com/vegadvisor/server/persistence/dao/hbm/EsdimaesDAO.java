/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Esdimaes;
import com.vegadvisor.server.persistence.bo.EsdimaesId;
import com.vegadvisor.server.persistence.dao.IEsdimaesDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.HibernateFactory;

/**
 * @author VegAdvisor DAO para manejar la tabla de Esdimaes
 */
public class EsdimaesDAO extends GenericHbmDAO<Esdimaes, EsdimaesId> implements
		IEsdimaesDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EsdimaesDAO() {
		super(Esdimaes.class);
	}

	/**
	 * Metodo para guardar un objeto en la base de datos
	 * 
	 * @param obj
	 *            objeto a guardar
	 * @throws HibernateException
	 */
	public void save(Esdimaes obj) throws DAOException {
		// Sesion y transaccion locales
		Session local_session = null;
		Transaction local_tx = null;
		try {
			if (!reuseSession) {/* No re-usa */
				// Inicia nueva sesion y transaccion
				local_session = HibernateFactory.openSession();
				local_tx = local_session.beginTransaction();
			} else {/* Esta re-usando */
				// Asigna a variables temporales
				local_session = session;
				local_tx = tx;
			}
			// Obtiene ultima secuencia
			Criteria criteria = local_session.createCriteria(this.clase);
			criteria.add(Restrictions.eq("id.estcestnk", obj.getId()
					.getEstcestnk()));
			criteria.setProjection(Projections.max("id.iesciesnk"));
			// Asigna secuencia
			Object zeroValue = criteria.list().get(0);
			int lastSec = zeroValue != null ? ((Integer) zeroValue).intValue()
					: 0;
			// Asigna secuencia
			obj.getId().setIesciesnk(lastSec + 1);
			// Guarda objeto
			local_session.save(obj);
			if (!this.getReuseSession())/* Solo si no esta re-usando */
				local_tx.commit();
		} catch (Exception e) {
			handleException(e, local_tx);
		} finally {
			if (!this.getReuseSession())/* Solo si no esta re-usando */
				HibernateFactory.close(local_session);
		}
	}

	/**
	 * Carga las imagenes de un establecimiento
	 * 
	 * @param estcestnk
	 *            Codigo del establecimiento
	 * @return Lista de imagenes del establecimiento
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Esdimaes> findByEstablishment(Integer estcestnk)
			throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("id.estcestnk", estcestnk));
		return this.findByCriteria(crit);
	}
}
