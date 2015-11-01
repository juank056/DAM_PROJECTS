/**
 * 
 */
package com.vegadvisor.server.persistence.dao.hbm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Esmestab;
import com.vegadvisor.server.persistence.dao.IEsmestabDAO;
import com.vegadvisor.server.persistence.dao.hbm.base.GenericHbmDAO;
import com.vegadvisor.server.utils.Constants;

/**
 * @author VegAdvisor DAO para manejar la tabla de Esmestab
 */
public class EsmestabDAO extends GenericHbmDAO<Esmestab, Integer> implements
		IEsmestabDAO {

	/**
	 * ID Serializacion
	 */
	private static final long serialVersionUID = 1L;

	public EsmestabDAO() {
		super(Esmestab.class);
	}

	/**
	 * Busca establecimientos cercanos a una posicion y que cumplan con un
	 * nombre
	 * 
	 * @param estnestaf
	 *            nombre del establecimiento
	 * @param ratio
	 *            radio de busqueda en kilometros
	 * @param latitud
	 *            latitud del punto de busqueda
	 * @param longitud
	 *            longitud del punto de busqueda
	 * @return Lista de establecimientos que cumplen con el nombre y que estan
	 *         dentro del rango de busqueda
	 * @throws DAOException
	 *             error en la base de datos
	 */
	public List<Esmestab> findByNameAndPosition(String estnestaf, double ratio,
			double latitud, double longitud) throws DAOException {
		// Inicia parametros de la query
		Map<String, Object> parameters = new HashMap<String, Object>();
		// Consulta a realizar
		String query = "select estab from Esmestab estab"
				+ " where estab.estnestaf like :nomEstab and "
				+ "(6371*ACOS(COS( RADIANS(:mylatitud)) * "
				+ "COS(RADIANS(estlatinf)) * COS(RADIANS(estlongnf) "
				+ "- RADIANS(:mylongitud) )+ SIN( RADIANS(:mylatitud))"
				+ "*SIN(RADIANS(estlatinf)))) <= :myratio "
				+ "order by estab.estnestaf asc";
		// Asigna parametros
		parameters.put("mylatitud", latitud);
		parameters.put("mylongitud", longitud);
		parameters.put("nomEstab", Constants.PERCENTAGE + estnestaf
				+ Constants.PERCENTAGE);
		parameters.put("myratio", ratio);
		// Ejecuta consulta
		return this.findByQuery(query, parameters);
	}

	/**
	 * Busca los establecimientos de un usuario
	 * 
	 * @param usucusuak
	 *            Codigo de usuario
	 * @return Establecimientos creados por un usuario
	 * @throws DAOException
	 *             Error en la base de datos
	 */
	public List<Esmestab> findByUser(String usucusuak) throws DAOException {
		// Crea los criterios
		List<Criterion> crit = new ArrayList<Criterion>();
		crit.add(Restrictions.eq("usucusuak", usucusuak));
		return this.findByCriteria(crit);
	}
}
