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
	 * Busca eventos por una pista y una posicion dentro de un radio de b�squeda
	 * De los eventos se retorna tambi�n el indicador de participaci�n del
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
	 * @return Lista de eventos que cumplen la condici�n (que no hayan pasado
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
					// Cero en indicador de participaci�n
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
	 * M�todo para ingresar un nuevo evento en el sistema
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param countryCode
	 *            C�digo del pa�s del evento
	 * @param cityCode
	 *            C�digo de la ciudad del evento
	 * @param eventName
	 *            Nombre del evento
	 * @param dateEvent
	 *            Fecha del evento
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
	 * @return Retorno de validaci�n de creaci�n de evento
	 */
	@Override
	public ReturnValidation createEvent(String userId, String countryCode,
			String cityCode, String eventName, Date dateEvent,
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
			// Usuario que crea el evento
			event.setUsucusuak(userId);
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
			// Descripci�n evento
			event.setEvedeveaf(eventName);
			// Numero de participantes
			event.setEvenparnf(0);
			// Guarda registro en la base de datos
			evmeventDao.save(event);
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
	 * Registra o actualiza la participaci�n de un usuario en un evento
	 * 
	 * @param countryCode
	 *            C�digo de pais del evento
	 * @param cityCode
	 *            C�digo de la ciudad del evento
	 * @param eventDate
	 *            Fecha del evento
	 * @param eventSec
	 *            Secuencia de evento del d�a
	 * @param userId
	 *            Id del usuario
	 * @param participationInd
	 *            Indicador de participaci�n en el evento
	 * @return Retorno de validaci�n
	 */
	@Override
	public ReturnValidation registerUsersEventParticipation(String countryCode,
			String cityCode, Date eventDate, int eventSec, String userId,
			String participationInd) {
		try {
			// Inicia DAOS
			initDaos();
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
			// Obtiene evento padre
			Evmevent event = evmeventDao.findById(new EvmeventId(countryCode,
					cityCode, eventDate, eventSec));
			// Si el indicador de participaci�n es '2' (Asistire, incrementa)
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
