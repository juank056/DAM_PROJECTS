/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.bo.Cspciuda;
import com.vegadvisor.server.persistence.bo.CspciudaId;
import com.vegadvisor.server.persistence.bo.Csptiest;
import com.vegadvisor.server.persistence.bo.Csptieve;
import com.vegadvisor.server.persistence.bo.Csptpais;
import com.vegadvisor.server.persistence.dao.ICspciudaDAO;
import com.vegadvisor.server.persistence.dao.ICsptiestDAO;
import com.vegadvisor.server.persistence.dao.ICsptieveDAO;
import com.vegadvisor.server.persistence.dao.ICsptpaisDAO;
import com.vegadvisor.server.services.IBasicTablesServices;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios de las tablas b�sicas del sistema
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
	 * @param countryCode
	 *            C�digo de pais
	 * @param clue
	 *            Pista del nombre del pais
	 * @return Lista de paises que cumplen con el criterio
	 */
	@Override
	public List<Csptpais> getCountries(String countryCode, String clue) {
		// Retorno
		List<Csptpais> response = new ArrayList<Csptpais>();
		try {
			// Inicia DAOS
			initDaos();
			// Revisa si viene Id
			if (countryCode != null && !Constants.BLANKS.equals(countryCode)) {
				// Busca por Id
				Csptpais tpais = csptpaisDao.findById(countryCode);
				// Revisa si encuentra
				if (tpais != null) {/* Encontrado */
					// Limpia
					tpais.cleanObject();
					// Adiciona elemento a la lista
					response.add(tpais);
					// Retorna
					return response;
				}
			}
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
	 *            C�digo del pais
	 * @param cityCode
	 *            C�digo de Ciudad
	 * @param clue
	 *            Pista del nombre de la ciudad
	 * @return Lista de ciudades que cumplen con las condiciones
	 */
	@Override
	public List<Cspciuda> getCities(String countryCode, String cityCode,
			String clue) {
		// Retorno
		List<Cspciuda> response = new ArrayList<Cspciuda>();
		try {
			// Inicia DAOS
			initDaos();
			// Revisa si viene Id
			if (cityCode != null && !Constants.BLANKS.equals(cityCode)) {
				// Busca por Id
				Cspciuda ciuda = cspciudaDao.findById(new CspciudaId(
						countryCode, cityCode));
				// Revisa si encuentra
				if (ciuda != null) {/* Encontrado */
					// Limpia
					ciuda.cleanObject();
					// Adiciona elemento a la lista
					response.add(ciuda);
					// Retorna
					return response;
				}
			}
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
	 * @param establishmentTypeId
	 *            Id del tipo de establecimiento
	 * @param clue
	 *            Pista del nombre de tipo establecimiento
	 * @return Lista de tipos de establecimiento
	 */
	@Override
	public List<Csptiest> getEstablishmentTypes(int establishmentTypeId,
			String clue) {
		// Retorno
		List<Csptiest> response = new ArrayList<Csptiest>();
		try {
			// Inicia DAOS
			initDaos();
			// Revisa si viene Id
			if (establishmentTypeId != 0) {
				// Busca por Id
				Csptiest tiest = csptiestDao.findById(establishmentTypeId);
				// Revisa si encuentra
				if (tiest != null) {/* Encontrado */
					// Limpia
					tiest.cleanObject();
					// Adiciona elemento a la lista
					response.add(tiest);
					// Retorna
					return response;
				}
			}
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
	 * @param eventTypeId
	 *            Id del tipo de evento
	 * @param clue
	 *            Pista del nombre de tipo de evento
	 * @return Lista de tipos de eventos
	 */
	@Override
	public List<Csptieve> getEventTypes(int eventTypeId, String clue) {
		// Retorno
		List<Csptieve> response = new ArrayList<Csptieve>();
		try {
			// Inicia DAOS
			initDaos();
			// Revisa si viene Id
			if (eventTypeId != 0) {
				// Busca por Id
				Csptieve tieve = csptieveDao.findById(eventTypeId);
				// Revisa si encuentra
				if (tieve != null) {/* Encontrado */
					// Limpia
					tieve.cleanObject();
					// Adiciona elemento a la lista
					response.add(tieve);
					// Retorna
					return response;
				}
			}
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
	 * M�todo para iniciar los Daos del servicio
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
