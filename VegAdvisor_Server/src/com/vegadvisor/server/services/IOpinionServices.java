/**
 * 
 */
package com.vegadvisor.server.services;

import java.util.List;

import com.vegadvisor.server.persistence.bo.Esdopies;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * @author JuanCamilo
 *
 */
public interface IOpinionServices {

	/**
	 * M�todo para registrar una opini�n de un usuario en un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param userId
	 *            Id del usuario que registra la opinion
	 * @param stars
	 *            N�mero de estrellas en la opinion
	 * @param opinion
	 *            Detalle de la opini�n
	 * @return Retorno de validaci�n
	 */
	public ReturnValidation registerUsersOpinion(int establishmentId,
			String userId, int stars, String opinion);

	/**
	 * Obtiene las ultimas maxOpinions de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param maxOpinions
	 *            N�mero m�ximo de op�niones a traer
	 * @return Lista de opiniones registradas para el establecimiento
	 */
	public List<Esdopies> findEstablishmentsOpinions(int establishmentId,
			int maxOpinions);

}
