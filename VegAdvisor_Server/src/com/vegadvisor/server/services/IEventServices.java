/**
 * 
 */
package com.vegadvisor.server.services;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.bo.Evmevent;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * Interfaz que define los servicios relacionados con los eventos registrados en
 * el sistema
 * 
 * @author JuanCamilo
 *
 */
public interface IEventServices {

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
	public List<Evmevent> findEvents(String userId, String clue,
			double latitud, double longitud, double ratio);

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
	 * @return Retorno de validaci�n de creaci�n de evento
	 */
	public ReturnValidation createEvent(String userId, String countryCode,
			String cityCode, String eventName, Date dateEvent, Date timeEvent,
			int establishmentId, double latitud, double longitud,
			String placeName, int eventType);

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
	public ReturnValidation registerUsersEventParticipation(String countryCode,
			String cityCode, Date eventDate, int eventSec, String userId,
			String participationInd);

	/**
	 * Obtiene los eventos de un usuario
	 * 
	 * @param userId
	 *            Id del usuario
	 * @return Lista de eventos del usuario
	 */
	public List<Evmevent> findUserEvents(String userId);

	/**
	 * M�todo para actualizar a un evento del sistema
	 * 
	 * @param countryCode
	 *            C�digo de pais del evento
	 * @param cityCode
	 *            C�digo de ciudad del evento
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
	 *            Indicador de si el evento est� activo o no
	 * @return Retorno de validaci�n
	 */
	public ReturnValidation updateEvent(String countryCode, String cityCode,
			Date eventDate, int eventSec, Date timeEvent, String eventName,
			int establishmentId, double latitud, double longitud,
			String placeName, int eventType, String isActive);

}
