/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Evdimaev;
import com.vegadvisor.server.persistence.bo.EvdimaevId;
import com.vegadvisor.server.persistence.dao.IEvdimaevDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.HibernateFactory;

/**
 * @author VegAdvisor DAO para manejar la tabla de Evdimaev
 */
public class EvdimaevDAO extends GenericHbmDAO<Evdimaev, EvdimaevId> implements
		IEvdimaevDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EvdimaevDAO() {
		super(Evdimaev.class);
	}

	/**
	 * Metodo para guardar un objeto en la base de datos
	 * 
	 * @param obj
	 *            objeto a guardar
	 * @throws HibernateException
	 */
	public void save(Evdimaev obj) throws DAOException {
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
			criteria.add(Restrictions.eq("id.paicpaiak", obj.getId()
					.getPaicpaiak()));
			criteria.add(Restrictions.eq("id.ciucciuak", obj.getId()
					.getCiucciuak()));
			criteria.add(Restrictions.eq("id.evefevefk", obj.getId()
					.getEvefevefk()));
			criteria.add(Restrictions.eq("id.evecevenk", obj.getId()
					.getEvecevenk()));
			criteria.setProjection(Projections.max("id.imecimenk"));
			// Asigna secuencia
			Object zeroValue = criteria.list().get(0);
			int lastSec = zeroValue != null ? ((Integer) zeroValue).intValue()
					: 0;
			// Asigna secuencia
			obj.getId().setImecimenk(lastSec + 1);
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
	 * Obiene imagenes de un evento
	 * 
	 * @param paicpaiak
	 *            Pais del evento
	 * @param ciucciuak
	 *            Ciudad del evento
	 * @param evefevefk
	 *            Fecha del evento
	 * @param evecevenk
	 *            Consecutivo del evento
	 * @return Lista de imagenes del evento
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Evdimaev> findByEvent(String paicpaiak, String ciucciuak,
			Date evefevefk, int evecevenk) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("id.paicpaiak", paicpaiak));
		crit.add(Restrictions.eq("id.ciucciuak", ciucciuak));
		crit.add(Restrictions.eq("id.evefevefk", evefevefk));
		crit.add(Restrictions.eq("id.evecevenk", evecevenk));
		return this.findByCriteria(crit);
	}
}
