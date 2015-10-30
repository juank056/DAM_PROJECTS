/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.List;

import com.vegadvisor.server.persistence.bo.Cspciuda;
import com.vegadvisor.server.persistence.bo.Csptiest;
import com.vegadvisor.server.persistence.bo.Csptieve;
import com.vegadvisor.server.persistence.bo.Csptpais;
import com.vegadvisor.server.services.IBasicTablesServices;

/**
 * Servicios de las tablas básicas del sistema
 * 
 * @author JuanCamilo
 *
 */
public class BasicTablesServices implements IBasicTablesServices {

	/**
	 * 
	 */
	public BasicTablesServices() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IBasicTablesServices#getCountries(java
	 * .lang.String)
	 */
	@Override
	public List<Csptpais> getCountries(String clue) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IBasicTablesServices#getCities(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public List<Cspciuda> getCities(String countryCode, String clue) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IBasicTablesServices#getEstablishmentTypes
	 * (java.lang.String)
	 */
	@Override
	public List<Csptiest> getEstablishmentTypes(String clue) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IBasicTablesServices#getEventTypes(java
	 * .lang.String)
	 */
	@Override
	public List<Csptieve> getEventTypes(String clue) {
		// TODO Auto-generated method stub
		return null;
	}

}
