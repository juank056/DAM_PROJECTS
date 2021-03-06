/**
 * 
 */
package com.vegadvisor.server.services;

import java.util.List;

import com.vegadvisor.server.persistence.bo.Fomhilfo;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * Interfaz que define los servicios requeridos para el foro
 * 
 * @author JuanCamilo
 *
 */
public interface IForumServices {

	/**
	 * M�todo para buscar los hilos de foro de acuerdo a una pista
	 * 
	 * @param clue
	 *            pista de b�squeda para los hilos de foro
	 * @return Lista de hilos de foro
	 */
	public List<Fomhilfo> findForumThreads(String clue);

	/**
	 * M�todo para iniciar un nuevo hilo de foro
	 * 
	 * @param userId
	 *            Id del usuario que registra el hilo
	 * @param threadTitle
	 *            titulo del hilo de foro
	 * @param threadDetail
	 *            Detalle del hilo
	 * @return Retorno de validaci�n
	 */
	public ReturnValidation createForumThread(String userId,
			String threadTitle, String threadDetail);

	/**
	 * M�todo para registrar la respuesta de un hilo de foro
	 * 
	 * @param userId
	 *            Id del usuario que registra la respuesta
	 * @param threadId
	 *            id del hilo que se esta respondiendo
	 * @param responseDetail
	 *            Detalle de la respuesta
	 * @return Retorno de validaci�n
	 */
	public ReturnValidation createForumThreadResponse(String userId,
			int threadId, String responseDetail);

}
