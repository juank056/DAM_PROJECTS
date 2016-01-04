/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Esdopies;
import com.vegadvisor.server.persistence.bo.Esmestab;
import com.vegadvisor.server.persistence.bo.Usdcheus;
import com.vegadvisor.server.persistence.dao.IEsdopiesDAO;
import com.vegadvisor.server.persistence.dao.IEsmestabDAO;
import com.vegadvisor.server.persistence.dao.IUsdcheusDAO;
import com.vegadvisor.server.services.IEstablishmentServices;
import com.vegadvisor.server.services.bo.EstablishmentStatistic;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.MessageBundle;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios de establecimientos
 * 
 * @author JuanCamilo
 *
 */
public class EstablishmentServices implements IEstablishmentServices {

	/**
	 * Dao para leer estructura ESMESTAB
	 */
	private IEsmestabDAO esmestabDao;

	/**
	 * Dao para leer estructura ESDOPIES
	 */
	private IEsdopiesDAO esdopiesDao;

	/**
	 * Dao para leer estructura USDCHEUS
	 */
	private IUsdcheusDAO usdcheusDao;

	/**
	 * Indicador de Daos iniciados
	 */
	private boolean daosInicialized;

	/**
	 * 
	 */
	public EstablishmentServices() {
		// Daos iniciados en false
		this.daosInicialized = false;
	}

	/**
	 * Método para buscar establecimientos del sistema
	 * 
	 * @param clue
	 *            Pista de establecimientos (para like)
	 * @param ratio
	 *            Radio de busqueda de establecimientos (En km)
	 * @param latitud
	 *            latitud actual de un usuario
	 * @param longitud
	 *            longitud actual de un usuario
	 * @return Lista de establecimientos que cumplen el criterio de búsqueda
	 */
	@Override
	public List<Esmestab> findEstablishments(String clue, double ratio,
			double latitud, double longitud) {
		// Retorno
		List<Esmestab> response = new ArrayList<Esmestab>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca establecimientos
			response = esmestabDao.findByNameAndPosition(clue, ratio, latitud,
					longitud);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Método para crear o actualizar establecimientos en el sistema
	 * 
	 * @param establishmentId
	 *            Id del establecimiento (cero es para creación)
	 * @param userId
	 *            Usuario que crea el establecimiento
	 * @param companyName
	 *            Nombre del establecimiento
	 * @param establishmentType
	 *            Tipo de establecimiento
	 * @param address
	 *            Dirección del establecimiento
	 * @param phones
	 *            Teléfonos del establecimiento
	 * @param openingTime
	 *            Horario de apertura
	 * @param closingTime
	 *            Horario de cierre
	 * @param country
	 *            Código de pais
	 * @param city
	 *            Código de ciudad
	 * @param latitud
	 *            Latitud del establecimiento
	 * @param longitud
	 *            Longitud del establecimiento
	 * @param description
	 *            Descripción del establecimiento
	 * @param active
	 *            Indicador de activo
	 * @return Indicador de creación/actualización y mensaje
	 */
	@Override
	public ReturnValidation createOrUpdateEstablishment(int establishmentId,
			String userId, String companyName, int establishmentType,
			String address, String phones, Date openingTime, Date closingTime,
			String country, String city, double latitud, double longitud,
			String description, String active) {
		try {
			// Inicia DAOS
			initDaos();
			// Busca registro por id del establecimiento
			Esmestab estab = esmestabDao.findById(establishmentId);
			if (estab == null) {/* Establecimiento no existe */
				// Crea nuevo establecimiento
				estab = new Esmestab();
				// Promedio de estrellas a cero
				estab.setEstpestnf(0);
			}
			// Asigna campos para actualización o creación
			// Usuario
			estab.setUsucusuak(userId);
			// Nombre del establecimiento
			estab.setEstnestaf(companyName);
			// Tipo de establecimiento
			estab.setTesctesnk(establishmentType);
			// Dirección del establecimiento
			estab.setEstdireaf(address);
			// Teléfono del establecimiento
			estab.setEstteleaf(phones);
			// Hora de apertura
			estab.setEsthoratf(openingTime);
			// Horario de cierre
			estab.setEsthorctf(closingTime);
			// Pais
			estab.setPaicpaiak(country);
			// Ciudad
			estab.setCiucciuak(city);
			// Latitud
			estab.setEstlatinf(latitud);
			// Longitud
			estab.setEstlongnf(longitud);
			// Descripcion
			estab.setEstdestaf(description);
			// Activo
			estab.setEstiactsf(active);
			// Guarda o actualiza registro en la base de datos
			esmestabDao.saveOrUpdate(estab);
			// Retorno
			ReturnValidation response = new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.util.msj001"));
			// Adiciona id del establecimiento
			response.getParams().put("establishmentId",
					Constants.BLANKS + estab.getEstcestnk());
			// Retorna
			return response;
		} catch (DAOException e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/**
	 * Obtiene lista de los establecimientos de un usuario
	 * 
	 * @param userId
	 *            Id del usuario
	 * @return Lista de los establecimientos creados por un usuario
	 */
	@Override
	public List<Esmestab> getUserEstablishments(String userId) {
		// Retorno
		List<Esmestab> response = new ArrayList<Esmestab>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca establecimientos del usuario
			response = esmestabDao.findByUser(userId);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Obtiene estadisticas de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param sinceDate
	 *            Fecha desde de consulta
	 * @param untilDate
	 *            Fecha hasta de consulta
	 * @return Lista de estadisticas por día para el establecimiento
	 */
	@Override
	public List<EstablishmentStatistic> getEstablishmentStatistics(
			int establishmentId, Date sinceDate, Date untilDate) {
		// Retorno
		List<EstablishmentStatistic> response = new ArrayList<EstablishmentStatistic>();
		// indicador de terminado
		boolean isDone = false;
		try {
			// Inicia DAOS
			initDaos();
			// Obtiene el establecimiento
			Esmestab estab = esmestabDao.findById(establishmentId);
			// Revisa si se encuentra el establecimiento
			if (estab == null) {/* No encontrado */
				// Finaliza
				return response;
			}
			// Inicia lista de retorno con los elementos en ceros
			Date temp = DateUtils.moveUtilDate(sinceDate, 0);
			while (!isDone) {
				// Crea elemento de retorno
				EstablishmentStatistic statistic = new EstablishmentStatistic();
				// Id y nombre de establecimiento
				statistic.setEstablishmentId(establishmentId);
				statistic.setEstablishmentName(estab.getEstnestaf());
				// Asigna fecha del dia
				statistic.setDay(temp);
				// Mueve fecha un día
				temp = DateUtils.moveUtilDate(temp, 1);
				// Adiciona elemento a la lista
				response.add(statistic);
				// Revisa si ya termino
				if (DateUtils.getDateStringYYYYMMDD(temp).equals(
						DateUtils.getDateStringYYYYMMDD(untilDate))) {
					// Done
					isDone = true;
					// Adiciona ultimo elemento
					statistic = new EstablishmentStatistic();
					// Asigna fecha del dia
					statistic.setDay(temp);
					// Id y nombre de establecimiento
					statistic.setEstablishmentId(establishmentId);
					statistic.setEstablishmentName(estab.getEstnestaf());
					// Adiciona elemento a la lista
					response.add(statistic);
				}
			}
			// Obtiene Checkins de usuarios en la fecha
			List<Usdcheus> cheuss = usdcheusDao.findByRange(establishmentId,
					sinceDate, untilDate);
			// Obtiene opiniones de los usuarios en el rango
			List<Esdopies> opiess = esdopiesDao.findByRange(establishmentId,
					sinceDate, untilDate);
			// Recorre lista de retorno para actualizar valores de estadisticas
			for (EstablishmentStatistic statistic : response) {
				Date day = statistic.getDay();
				// Recorre opiniones
				for (Esdopies opinion : opiess) {
					// Revisa si son de la misma fecha
					if (DateUtils.getDateString(day).equals(
							DateUtils.getDateString(opinion.getId()
									.getOesfregfk()))) {
						// Misma fecha
						// Incluye estrellas de opinion
						statistic.getOpinionStars().add(opinion.getOesnestnf());
					}
				}
				// Recorre Checkins
				for (Usdcheus cheus : cheuss) {
					// Revisa si son de la misma fecha
					if (DateUtils.getDateString(day).equals(
							DateUtils.getDateString(cheus.getId()
									.getChufregfk()))) {
						// Misma fecha
						// Incrementa checkin
						statistic.setCheckins(statistic.getCheckins() + 1);
					}
				}
			}
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
			esmestabDao = SpringAppContext.getAppContext().getBean(
					IEsmestabDAO.class);
			esdopiesDao = SpringAppContext.getAppContext().getBean(
					IEsdopiesDAO.class);
			usdcheusDao = SpringAppContext.getAppContext().getBean(
					IUsdcheusDAO.class);
			// Daos iniciados a true
			daosInicialized = true;
		}
	}
}
