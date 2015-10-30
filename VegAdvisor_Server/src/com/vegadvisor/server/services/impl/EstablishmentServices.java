/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.Date;
import java.util.List;

import com.vegadvisor.server.persistence.bo.Esmestab;
import com.vegadvisor.server.services.IEstablishmentServices;
import com.vegadvisor.server.services.bo.EstablishmentStatistic;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * Servicios de establecimientos
 * 
 * @author JuanCamilo
 *
 */
public class EstablishmentServices implements IEstablishmentServices {

	/**
	 * 
	 */
	public EstablishmentServices() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IEstablishmentServices#findEstablishments
	 * (java.lang.String, double, double, double)
	 */
	@Override
	public List<Esmestab> findEstablishments(String clue, double ratio,
			double latitud, double longitud) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vegadvisor.server.services.IEstablishmentServices#
	 * createOrUpdateEstablishment(int, java.lang.String, int, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, double, double,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ReturnValidation createOrUpdateEstablishment(int establishmentId,
			String companyName, int establishmentType, String address,
			String phones, String country, String city, double latitud,
			double longitud, String description, String active) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IEstablishmentServices#getUserEstablishments
	 * (java.lang.String)
	 */
	@Override
	public List<Esmestab> getUserEstablishments(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vegadvisor.server.services.IEstablishmentServices#
	 * getEstablishmentStatistics(int, java.util.Date, java.util.Date)
	 */
	@Override
	public List<EstablishmentStatistic> getEstablishmentStatistics(
			int establishmentId, Date sinceDate, Date untilDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
