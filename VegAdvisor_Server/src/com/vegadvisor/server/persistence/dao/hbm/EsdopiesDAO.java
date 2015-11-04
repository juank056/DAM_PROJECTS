/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Esdopies;
import com.vegadvisor.server.persistence.bo.EsdopiesId;
import com.vegadvisor.server.persistence.dao.IEsdopiesDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.HibernateFactory;

/**
 * @author VegAdvisor DAO para manejar la tabla de Esdopies
 */
public class EsdopiesDAO extends GenericHbmDAO<Esdopies, EsdopiesId> implements
		IEsdopiesDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EsdopiesDAO() {
		super(Esdopies.class);
	}

	/**
	 * Metodo para guardar un objeto en la base de datos
	 * 
	 * @param obj
	 *            objeto a guardar
	 * @throws HibernateException
	 */
	public void save(Esdopies obj) throws DAOException {
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
			criteria.add(Restrictions.eq("id.oesfregfk", obj.getId()
					.getOesfregfk()));
			criteria.setProjection(Projections.max("id.oescoesnk"));
			// Asigna secuencia
			Object zeroValue = criteria.list().get(0);
			int lastSec = zeroValue != null ? ((Integer) zeroValue).intValue()
					: 0;
			// Asigna secuencia
			obj.getId().setOescoesnk(lastSec + 1);
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
	 * Busca opiniones de usuarios en un establecmiento para un rango de fechas
	 * 
	 * @param estcestnk
	 *            Id del establecimiento a buscar
	 * @param sinceDate
	 *            fecha desde de la busqueda
	 * @param untilDate
	 *            fecha hasta de la busqueda
	 * @return Lista de opiniones de usuarios en el rango establecido
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Esdopies> findByRange(int estcestnk, Date sinceDate,
			Date untilDate) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("id.estcestnk", estcestnk));
		crit.add(Restrictions.ge("id.oesfregfk", sinceDate));
		crit.add(Restrictions.le("id.oesfregfk", untilDate));
		return this.findByCriteria(crit);
	}

	/**
	 * Busca los registros de opiniones para un establecimiento
	 * 
	 * @param estcestnk
	 *            Codigo del establecimiento
	 * @param maxOpinions
	 *            Maximo de opiniones a traer
	 * @return Lista de opiniones del establecimiento
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Esdopies> findByEstablishment(int estcestnk, int maxOpinions)
			throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("id.estcestnk", estcestnk));
		return this.findByCriteria(crit, Order.desc("id.oesfregfk"),
				maxOpinions);
	}

	/**
	 * Calcula el promedio de estrellas de opinión para un establecimiento
	 * 
	 * @param estcestnk
	 *            Código del establecimiento
	 * @return Promedio de estrellas para el establecimiento
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public double calculateAverageStars(int estcestnk) throws DAOException {
		// Sesion y transaccion locales
		Session local_session = null;
		Transaction local_tx = null;
		// Average
		double average = 0;
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
			Query query = local_session
					.createQuery("select avg(op.oesnestnf) from Esdopies op where op.id.estcestnk = :estab");
			query.setParameter("estab", estcestnk);
			average = (Double) query.uniqueResult();
			if (!this.getReuseSession())/* Solo si no esta re-usando */
				local_tx.commit();
		} catch (Exception e) {
			handleException(e, local_tx);
		} finally {
			if (!this.getReuseSession())/* Solo si no esta re-usando */
				HibernateFactory.close(local_session);
		}

		return average;
	}
}
