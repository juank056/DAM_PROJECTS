/**
 * 
 */
package com.vegadvisor.server.services;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.bo.Evmevent;

/**
 * Interfaz que define los servicios relacionados con los eventos registrados en
 * el sistema
 * 
 * @author JuanCamilo
 *
 */
public interface IEventServices {

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
	public List<Evmevent> findEvents(String userId, String clue,
			double latitud, double longitud, double ratio);

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
	public String[] createEvent(String userId, String countryCode,
			String cityCode, String eventName, Date dateEvent,
			int establishmentId, double latitud, double longitud,
			String placeName, int eventType);

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
	public String[] registerUsersEventParticipation(String countryCode,
			String cityCode, Date eventDate, int eventSec, String userId,
			String participationInd);

}
