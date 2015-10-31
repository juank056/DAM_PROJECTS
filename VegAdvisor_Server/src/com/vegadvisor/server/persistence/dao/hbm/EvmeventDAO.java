/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Evmevent;
import com.vegadvisor.server.persistence.bo.EvmeventId;
import com.vegadvisor.server.persistence.dao.IEvmeventDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.HibernateFactory;

/**
 * @author VegAdvisor DAO para manejar la tabla de Evmevent
 */
public class EvmeventDAO extends GenericHbmDAO<Evmevent, EvmeventId> implements
		IEvmeventDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EvmeventDAO() {
		super(Evmevent.class);
	}

	/**
	 * Metodo para guardar un objeto en la base de datos
	 * 
	 * @param obj
	 *            objeto a guardar
	 * @throws HibernateException
	 */
	public void save(Evmevent obj) throws DAOException {
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
			criteria.setProjection(Projections.max("id.evecevenk"));
			// Asigna secuencia
			Object zeroValue = criteria.list().get(0);
			int lastSec = zeroValue != null ? ((Integer) zeroValue).intValue()
					: 0;
			// Asigna secuencia
			obj.getId().setEvecevenk(lastSec + 1);
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
	 * Busca eventos por nombre y por cercania de localización
	 * 
	 * @param evedeveaf
	 *            Nombre del evento de pista
	 * @param latitud
	 *            Latitud de referencia
	 * @param longitud
	 *            Longitud de referencia
	 * @param ratio
	 *            Radio de busqueda
	 * @return Lista de eventos que cumplen con las condiciones
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Evmevent> findByNameAndPosition(String evedeveaf,
			double latitud, double longitud, double ratio) throws DAOException {
		// Inicia parametros de la query
		Map<String, Object> parameters = new HashMap<String, Object>();
		// Consulta a realizar
		String query = "select event from Evmevent event"
				+ " where event.evedeveaf like %:nomEvent% "
				+ "having (6371*ACOS(COS( RADIANS(:mylatitud)) * "
				+ "COS(RADIANS(evelatinf)) * COS(RADIANS(evelongnf) "
				+ "- RADIANS(:mylongitud) )+ SIN( RADIANS(:mylatitud))"
				+ "*SIN(RADIANS(evelatinf)))) <= :myratio "
				+ "order by event.evedeveaf asc";
		// Asigna parametros
		parameters.put("mylatitud", latitud);
		parameters.put("mylongitud", longitud);
		parameters.put("nomEvent", evedeveaf);
		parameters.put("myratio", ratio);
		// Ejecuta consulta
		return this.findByQuery(query, parameters);
	}
}
