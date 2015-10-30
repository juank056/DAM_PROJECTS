/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.List;

import com.vegadvisor.server.persistence.bo.Esdopies;
import com.vegadvisor.server.services.IOpinionServices;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * Servicios de opiniones
 * 
 * @author JuanCamilo
 *
 */
public class OpinionServices implements IOpinionServices {

	/**
	 * 
	 */
	public OpinionServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ReturnValidation registerUsersOpinion(int establishmentId,
			String userId, int stars, String opinion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Esdopies> findEstablishmentsOpinions(int establishmentId,
			int maxOpinions) {
		// TODO Auto-generated method stub
		return null;
	}

}
