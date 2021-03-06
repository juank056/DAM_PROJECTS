/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Usdcheus;
import com.vegadvisor.server.persistence.bo.UsdcheusId;
import com.vegadvisor.server.persistence.dao.IUsdcheusDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.HibernateFactory;

/**
 * @author VegAdvisor DAO para manejar la tabla de Usdcheus
 */
public class UsdcheusDAO extends GenericHbmDAO<Usdcheus, UsdcheusId> implements
		IUsdcheusDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public UsdcheusDAO() {
		super(Usdcheus.class);
	}

	/**
	 * Metodo para guardar un objeto en la base de datos
	 * 
	 * @param obj
	 *            objeto a guardar
	 * @throws HibernateException
	 */
	public void save(Usdcheus obj) throws DAOException {
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
			criteria.add(Restrictions.eq("id.usucusuak", obj.getId()
					.getUsucusuak()));
			criteria.add(Restrictions.eq("id.estcestnk", obj.getId()
					.getEstcestnk()));
			criteria.add(Restrictions.eq("id.chufregfk", obj.getId()
					.getChufregfk()));
			criteria.setProjection(Projections.max("id.chucchunk"));
			// Asigna secuencia
			Object zeroValue = criteria.list().get(0);
			int lastSec = zeroValue != null ? ((Integer) zeroValue).intValue()
					: 0;
			// Asigna secuencia
			obj.getId().setChucchunk(lastSec + 1);
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
	 * Busca checkins de usuarios en un establecmiento para un rango de fechas
	 * 
	 * @param estcestnk
	 *            Id del establecimiento a buscar
	 * @param sinceDate
	 *            fecha desde de la busqueda
	 * @param untilDate
	 *            fecha hasta de la busqueda
	 * @return Lista de checkins de usuarios en el rango establecido
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Usdcheus> findByRange(int estcestnk, Date sinceDate,
			Date untilDate) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("id.estcestnk", estcestnk));
		crit.add(Restrictions.ge("id.chufregfk", sinceDate));
		crit.add(Restrictions.le("id.chufregfk", untilDate));
		return this.findByCriteria(crit);
	}
}
