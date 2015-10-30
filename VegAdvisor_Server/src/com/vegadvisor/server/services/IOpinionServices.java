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
	 * Método para registrar una opinión de un usuario en un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param userId
	 *            Id del usuario que registra la opinion
	 * @param stars
	 *            Número de estrellas en la opinion
	 * @param opinion
	 *            Detalle de la opinión
	 * @return Retorno de validación
	 */
	public ReturnValidation registerUsersOpinion(int establishmentId,
			String userId, int stars, String opinion);

	/**
	 * Obtiene las ultimas maxOpinions de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param maxOpinions
	 *            Número máximo de opíniones a traer
	 * @return Lista de opiniones registradas para el establecimiento
	 */
	public List<Esdopies> findEstablishmentsOpinions(int establishmentId,
			int maxOpinions);

}
