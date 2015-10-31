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
import com.vegadvisor.server.persistence.bo.Chdmensa;
import com.vegadvisor.server.persistence.bo.ChdmensaId;
import com.vegadvisor.server.persistence.dao.IChdmensaDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.HibernateFactory;
import com.vegadvisor.server.utils.Constants;

/**
 * @author VegAdvisor DAO para manejar la tabla de Chdmensa
 */
public class ChdmensaDAO extends GenericHbmDAO<Chdmensa, ChdmensaId> implements
		IChdmensaDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public ChdmensaDAO() {
		super(Chdmensa.class);
	}

	/**
	 * Metodo para guardar un objeto en la base de datos
	 * 
	 * @param obj
	 *            objeto a guardar
	 * @throws HibernateException
	 */
	public void save(Chdmensa obj) throws DAOException {
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
			criteria.add(Restrictions.eq("id.usucusdak", obj.getId()
					.getUsucusdak()));
			criteria.add(Restrictions.eq("id.mchfregfk", obj.getId()
					.getMchfregfk()));
			criteria.setProjection(Projections.max("id.mchcmchnk"));
			// Asigna secuencia
			Object zeroValue = criteria.list().get(0);
			int lastSec = zeroValue != null ? ((Integer) zeroValue).intValue()
					: 0;
			// Asigna secuencia
			obj.getId().setMchcmchnk(lastSec + 1);
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
	 * Busca los mensajes pendientes de entrega para un usuario
	 * 
	 * @param usucusdak
	 *            Usuario que debe recibir los mensajes
	 * @return Lista de mensajes pendientes de recepcion para el usuario
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Chdmensa> findUserPendingMessages(String usucusdak)
			throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("id.usucusdak", usucusdak));
		crit.add(Restrictions.eq("mchientsf", Constants.ZERO));
		return this.findByCriteria(crit);
	}
}
