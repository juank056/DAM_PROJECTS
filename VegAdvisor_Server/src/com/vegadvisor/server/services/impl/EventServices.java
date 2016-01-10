/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Evdevvus;
import com.vegadvisor.server.persistence.bo.EvdevvusId;
import com.vegadvisor.server.persistence.bo.Evmevent;
import com.vegadvisor.server.persistence.bo.EvmeventId;
import com.vegadvisor.server.persistence.dao.IEvdevvusDAO;
import com.vegadvisor.server.persistence.dao.IEvmeventDAO;
import com.vegadvisor.server.services.IEventServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.MessageBundle;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios de Eventos
 * 
 * @author JuanCamilo
 *
 */
public class EventServices implements IEventServices {

	/**
	 * DAO para trabajar con tabla EVMEVENT
	 */
	private IEvmeventDAO evmeventDao;

	/**
	 * DAO para trabajar con la tabla EVDEVVUS
	 */
	private IEvdevvusDAO evdevvusDao;

	/**
	 * Indicador de Daos iniciados
	 */
	private boolean daosInicialized;

	/**
	 * Constructor de servicio
	 */
	public EventServices() {
		// Daos iniciados en false
		this.daosInicialized = false;
	}

	/**
	 * Busca eventos por una pista y una posicion dentro de un radio de búsqueda
	 * De los eventos se retorna también el indicador de participación del
	 * usuario
	 * 
	 * @param userId
	 *            Id del usuario (para saber si participa o no en el evento)
	 * @param clue
	 *            Pista de nombre del evento
	 * @param latitud
	 *            Latitud de un usuario
	 * @param longitud
	 *            Longitud de un usuario
	 * @param ratio
	 *            Radio de busqueda de evento
	 * @return Lista de eventos que cumplen la condición (que no hayan pasado
	 *         aun)
	 */
	@Override
	public List<Evmevent> findEvents(String userId, String clue,
			double latitud, double longitud, double ratio) {
		// Retorno
		List<Evmevent> response = new ArrayList<Evmevent>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca usuarios de acuerdo clue y activos para chat
			response = evmeventDao.findByNameAndPosition(clue, latitud,
					longitud, ratio);
			// Recorre lista de eventos para ver si el usuario participa o no
			for (Evmevent event : response) {
				// Busca partcipacion del usuario
				Evdevvus evvus = evdevvusDao.findById(new EvdevvusId(event
						.getId().getPaicpaiak(), event.getId().getCiucciuak(),
						event.getId().getEvefevefk(), event.getId()
								.getEvecevenk(), userId));
				// Revisa si encuentra registro
				if (evvus != null) {/* Encontrado */
					// Asigna indicador de participacion
					event.setUserParticipating(evvus.getEvuindpvf());
				} else {/* No Encontrado */
					// Cero en indicador de participación
					event.setUserParticipating(Constants.ZERO);
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
	 * Método para ingresar un nuevo evento en el sistema
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param countryCode
	 *            Código del país del evento
	 * @param cityCode
	 *            Código de la ciudad del evento
	 * @param eventName
	 *            Nombre del evento
	 * @param dateEvent
	 *            Fecha del evento
	 * @param timeEvent
	 *            Hora del evento
	 * @param establishmentId
	 *            Id del establecimiento del evento (opcional)
	 * @param latitud
	 *            Latitud donde es el evento
	 * @param longitud
	 *            Longitud del evento
	 * @param placeName
	 *            Nombre del lugar donde es el evento
	 * @param eventType
	 *            Tipo de evento
	 * @return Retorno de validación de creación de evento
	 */
	@Override
	public ReturnValidation createEvent(String userId, String countryCode,
			String cityCode, String eventName, Date dateEvent, Date timeEvent,
			int establishmentId, double latitud, double longitud,
			String placeName, int eventType) {
		try {
			// Inicia DAOS
			initDaos();
			// Inicia registro evmevent
			Evmevent event = new Evmevent();
			// llave del evento
			EvmeventId id = new EvmeventId(countryCode, cityCode, dateEvent, 0);
			// Asigna llave
			event.setId(id);
			// Asigna datos adicionales del evento
			// Hora del evento
			event.setEvehoratf(timeEvent);
			// Usuario que crea el evento
			event.setUsucusuak(userId);
			// establecimiento del evento
			event.setEstcestnk(establishmentId);
			// Localizacion evento
			event.setEstloceaf(placeName);
			// Latitud evento
			event.setEvelatinf(latitud);
			// Longitud evento
			event.setEvelongnf(longitud);
			// Tipo de evento
			event.setTevctevnk(eventType);
			// Descripción evento
			event.setEvedeveaf(eventName);
			// Numero de participantes
			event.setEvenparnf(0);
			// Indicador de activo en si
			event.setEveiactsf(Constants.ONE);
			// Guarda registro en la base de datos
			evmeventDao.save(event);
			// Retorno
			ReturnValidation response = new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.util.msj001"));
			// Parametro de llave
			response.getParams().put("paicpaiak", event.getId().getPaicpaiak());
			response.getParams().put("ciucciuak", event.getId().getCiucciuak());
			response.getParams().put(
					"evefevefk",
					DateUtils.getDateStringYYYYMMDD(event.getId()
							.getEvefevefk()));
			response.getParams().put("evecevenk",
					Constants.BLANKS + event.getId().getEvecevenk());
			// Retorna respuesta
			return response;
		} catch (DAOException e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/**
	 * Registra o actualiza la participación de un usuario en un evento
	 * 
	 * @param countryCode
	 *            Código de pais del evento
	 * @param cityCode
	 *            Código de la ciudad del evento
	 * @param eventDate
	 *            Fecha del evento
	 * @param eventSec
	 *            Secuencia de evento del día
	 * @param userId
	 *            Id del usuario
	 * @param participationInd
	 *            Indicador de participación en el evento
	 * @return Retorno de validación
	 */
	@Override
	public ReturnValidation registerUsersEventParticipation(String countryCode,
			String cityCode, Date eventDate, int eventSec, String userId,
			String participationInd) {
		try {
			// Inicia DAOS
			initDaos();
			// Obtiene evento padre
			Evmevent event = evmeventDao.findById(new EvmeventId(countryCode,
					cityCode, eventDate, eventSec));
			// Revisa que encuentre el evento
			if (event == null) {/* Evento no encontrado */
				return new ReturnValidation(Constants.ZERO,
						MessageBundle.getMessage("com.vegadvisor.util.msj004"));
			}
			// Crea registro de EVDEVVUS
			Evdevvus evvus = new Evdevvus();
			// Llave del registro
			EvdevvusId id = new EvdevvusId(countryCode, cityCode, eventDate,
					eventSec, userId);
			// Asigna llave
			evvus.setId(id);
			// Indicador de participacion
			evvus.setEvuindpvf(participationInd);
			// Guarda o actualiza registro
			evdevvusDao.saveOrUpdate(evvus);
			// Si el indicador de participación es '2' (Asistire, incrementa)
			// Si es '0', decrementa
			if (Constants.TWO.equals(participationInd)) {/* Participa */
				// Incrementa gente participando
				event.setEvenparnf(event.getEvenparnf() + 1);
			} else if (Constants.ZERO.equals(participationInd)) { /* No participa */
				// Decrementa gente participando
				event.setEvenparnf(event.getEvenparnf() - 1);
			}
			// Actualiza registro en la base de datos
			evmeventDao.update(event);
			// Retorno
			return new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.util.msj001"));
		} catch (DAOException e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/**
	 * Obtiene los eventos de un usuario
	 * 
	 * @param userId
	 *            Id del usuario
	 * @return Lista de eventos del usuario
	 */
	public List<Evmevent> findUserEvents(String userId) {
		// Retorno
		List<Evmevent> response = new ArrayList<Evmevent>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca eventos del usuario
			response = evmeventDao.findUserEvents(userId);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Método para actualizar a un evento del sistema
	 * 
	 * @param countryCode
	 *            Código de pais del evento
	 * @param cityCode
	 *            Código de ciudad del evento
	 * @param eventDate
	 *            Fecha del evento
	 * @param eventSec
	 *            Secuencia del evento
	 * @param timeEvent
	 *            Hora del evento
	 * @param eventName
	 *            Nombre del evento
	 * @param establishmentId
	 *            Id del establecimiento del evento
	 * @param latitud
	 *            Latitud del evento
	 * @param longitud
	 *            Longitud del evento
	 * @param placeName
	 *            Nombre del lugar donde es el evento
	 * @param eventType
	 *            Tipo de evento
	 * @param isActive
	 *            Indicador de si el evento esta activo o no
	 * @return Retorno de validación
	 */
	public ReturnValidation updateEvent(String countryCode, String cityCode,
			Date eventDate, int eventSec, Date timeEvent, String eventName,
			int establishmentId, double latitud, double longitud,
			String placeName, int eventType, String isActive) {
		try {
			// Inicia DAOS
			initDaos();
			// Obtiene evento
			Evmevent event = evmeventDao.findById(new EvmeventId(countryCode,
					cityCode, eventDate, eventSec));
			// Revisa que encuentre el evento
			if (event == null) {/* Evento no encontrado */
				return new ReturnValidation(Constants.ZERO,
						MessageBundle.getMessage("com.vegadvisor.util.msj004"));
			}
			// Actualiza datos del evento
			// Hora del evento
			event.setEvehoratf(timeEvent);
			// establecimiento del evento
			event.setEstcestnk(establishmentId);
			// Localizacion evento
			event.setEstloceaf(placeName);
			// Latitud evento
			event.setEvelatinf(longitud);
			// Longitud evento
			event.setEvelongnf(longitud);
			// Tipo de evento
			event.setTevctevnk(eventType);
			// Descripción evento
			event.setEvedeveaf(eventName);
			// Indicador activo
			event.setEveiactsf(isActive);
			// Actualiza registro en la base de datos
			evmeventDao.update(event);
			// Retorno
			return new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.util.msj001"));
		} catch (DAOException e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/**
	 * Inicia los DAos si no han sido iniciados
	 */
	private void initDaos() {
		// Inicia DAOs si no han sido iniciados
		if (!daosInicialized) {
			evmeventDao = SpringAppContext.getAppContext().getBean(
					IEvmeventDAO.class);
			evdevvusDao = SpringAppContext.getAppContext().getBean(
					IEvdevvusDAO.class);
			// Daos iniciados a true
			daosInicialized = true;
		}
	}

}
