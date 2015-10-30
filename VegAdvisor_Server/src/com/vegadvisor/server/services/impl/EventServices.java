/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.bo.Evmevent;
import com.vegadvisor.server.services.IEventServices;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * Servicios de Eventos
 * 
 * @author JuanCamilo
 *
 */
public class EventServices implements IEventServices {

	/**
	 * 
	 */
	public EventServices() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IEventServices#findEvents(java.lang.String
	 * , java.lang.String, double, double, double)
	 */
	@Override
	public List<Evmevent> findEvents(String userId, String clue,
			double latitud, double longitud, double ratio) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IEventServices#createEvent(java.lang.String
	 * , java.lang.String, java.lang.String, java.lang.String, java.util.Date,
	 * int, double, double, java.lang.String, int)
	 */
	@Override
	public ReturnValidation createEvent(String userId, String countryCode,
			String cityCode, String eventName, Date dateEvent,
			int establishmentId, double latitud, double longitud,
			String placeName, int eventType) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IEventServices#registerUsersEventParticipation
	 * (java.lang.String, java.lang.String, java.util.Date, int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ReturnValidation registerUsersEventParticipation(String countryCode,
			String cityCode, Date eventDate, int eventSec, String userId,
			String participationInd) {
		// TODO Auto-generated method stub
		return null;
	}

}
