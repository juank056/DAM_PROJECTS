/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.bo.Cspciuda;
import com.vegadvisor.server.persistence.bo.Csptiest;
import com.vegadvisor.server.persistence.bo.Csptieve;
import com.vegadvisor.server.persistence.bo.Csptpais;
import com.vegadvisor.server.persistence.dao.ICspciudaDAO;
import com.vegadvisor.server.persistence.dao.ICsptiestDAO;
import com.vegadvisor.server.persistence.dao.ICsptieveDAO;
import com.vegadvisor.server.persistence.dao.ICsptpaisDAO;
import com.vegadvisor.server.services.IBasicTablesServices;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios de las tablas básicas del sistema
 * 
 * @author JuanCamilo
 *
 */
public class BasicTablesServices implements IBasicTablesServices {

	/**
	 * Dao para leer estructura CSPTPAIS
	 */
	private ICsptpaisDAO csptpaisDao;

	/**
	 * Dao para leer estructura CSPCIUDA
	 */
	private ICspciudaDAO cspciudaDao;

	/**
	 * Dao para leer estructura CSPTIEST
	 */
	private ICsptiestDAO csptiestDao;

	/**
	 * Dao para leer estructura CSPTIEVE
	 */
	private ICsptieveDAO csptieveDao;

	/**
	 * Indicador de Daos iniciados
	 */
	private boolean daosInicialized;

	/**
	 * Constructor del servicio
	 */
	public BasicTablesServices() {
		// Daos iniciados en false
		this.daosInicialized = false;
	}

	/**
	 * Obtiene lista de paises de acuerdo a una pista del nombre
	 * 
	 * @param clue
	 *            Pista del nombre del pais
	 * @return Lista de paises que cumplen con el criterio
	 */
	@Override
	public List<Csptpais> getCountries(String clue) {
		// Retorno
		List<Csptpais> response = new ArrayList<Csptpais>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca paises de acuerdo clue
			response = csptpaisDao.findByName(clue);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Obtiene ciudades de acuerdo a un pais y una pista del nombre
	 * 
	 * @param countryCode
	 *            Código del pais
	 * @param clue
	 *            Pista del nombre de la ciudad
	 * @return Lista de ciudades que cumplen con las condiciones
	 */
	@Override
	public List<Cspciuda> getCities(String countryCode, String clue) {
		// Retorno
		List<Cspciuda> response = new ArrayList<Cspciuda>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca ciudades de acuerdo clue
			response = cspciudaDao.findByName(countryCode, clue);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Obtiene lista de tipos de establecimiento de acuerdo a una pista
	 * 
	 * @param clue
	 *            Pista del nombre de tipo establecimiento
	 * @return Lista de tipos de establecimiento
	 */
	@Override
	public List<Csptiest> getEstablishmentTypes(String clue) {
		// Retorno
		List<Csptiest> response = new ArrayList<Csptiest>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca Tipos de establecimiento de acuerdo clue
			response = csptiestDao.findByName(clue);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Obtiene lista de tipos de eventos de acuerdo a una pista
	 * 
	 * @param clue
	 *            Pista del nombre de tipo de evento
	 * @return Lista de tipos de eventos
	 */
	@Override
	public List<Csptieve> getEventTypes(String clue) {
		// Retorno
		List<Csptieve> response = new ArrayList<Csptieve>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca Tipos de evento de acuerdo clue
			response = csptieveDao.findByName(clue);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Método para iniciar los Daos del servicio
	 */
	private void initDaos() {
		// Inicia DAOs si no han sido iniciados
		if (!daosInicialized) {
			csptpaisDao = SpringAppContext.getAppContext().getBean(
					ICsptpaisDAO.class);
			cspciudaDao = SpringAppContext.getAppContext().getBean(
					ICspciudaDAO.class);
			csptiestDao = SpringAppContext.getAppContext().getBean(
					ICsptiestDAO.class);
			csptieveDao = SpringAppContext.getAppContext().getBean(
					ICsptieveDAO.class);
			// Daos iniciados a true
			daosInicialized = true;
		}
	}

}
