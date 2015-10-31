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
import com.vegadvisor.server.persistence.bo.Fodreshi;
import com.vegadvisor.server.persistence.bo.FodreshiId;
import com.vegadvisor.server.persistence.dao.IFodreshiDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.HibernateFactory;

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

	/**
	 * Metodo para guardar un objeto en la base de datos
	 * 
	 * @param obj
	 *            objeto a guardar
	 * @throws HibernateException
	 */
	public void save(Fodreshi obj) throws DAOException {
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
			criteria.add(Restrictions.eq("id.hifchifnk", obj.getId()
					.getHifchifnk()));
			criteria.setProjection(Projections.max("id.rhfcrhfnk"));
			// Asigna secuencia
			Object zeroValue = criteria.list().get(0);
			int lastSec = zeroValue != null ? ((Integer) zeroValue).intValue()
					: 0;
			// Asigna secuencia
			obj.getId().setRhfcrhfnk(lastSec + 1);
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
}
