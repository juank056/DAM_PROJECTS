/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Esdopies;
import com.vegadvisor.server.persistence.bo.EsdopiesId;
import com.vegadvisor.server.persistence.dao.IEsdopiesDAO;
import com.vegadvisor.server.services.IOpinionServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.MessageBundle;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios de opiniones
 * 
 * @author JuanCamilo
 *
 */
public class OpinionServices implements IOpinionServices {

	/**
	 * DAO para trabajar con tabla ESDOPIES
	 */
	private IEsdopiesDAO esdopiesDao;

	/**
	 * Indicador de Daos iniciados
	 */
	private boolean daosInicialized;

	/**
	 * 
	 */
	public OpinionServices() {
		// Daos iniciados en false
		this.daosInicialized = false;
	}

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
	@Override
	public ReturnValidation registerUsersOpinion(int establishmentId,
			String userId, int stars, String opinion) {
		try {
			// Inicia DAOS
			initDaos();
			// Crea registro ESDOPIES
			Esdopies opies = new Esdopies();
			// Llave del registro
			EsdopiesId id = new EsdopiesId(establishmentId,
					DateUtils.getCurrentUtilDate(), 0);
			// Asigna llave
			opies.setId(id);
			// Datos adicionales
			// Usuario que da la opinion
			opies.setUsucusuak(userId);
			// Numero de estrellas
			opies.setOesnestnf(stars);
			// Detalle opinion
			opies.setOesdetoaf(opinion);
			// Guarda registro en la base de datos
			esdopiesDao.save(opies);
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
	 * Obtiene las ultimas maxOpinions de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param maxOpinions
	 *            Número máximo de opíniones a traer
	 * @return Lista de opiniones registradas para el establecimiento
	 */
	@Override
	public List<Esdopies> findEstablishmentsOpinions(int establishmentId,
			int maxOpinions) {
		// Retorno
		List<Esdopies> response = new ArrayList<Esdopies>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca opiniones relacionadas al establecimiento
			response = esdopiesDao.findByEstablishment(establishmentId,
					maxOpinions);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Inicia los DAos si no han sido iniciados
	 */
	private void initDaos() {
		// Inicia DAOs si no han sido iniciados
		if (!daosInicialized) {
			esdopiesDao = SpringAppContext.getAppContext().getBean(
					IEsdopiesDAO.class);
			// Daos iniciados a true
			daosInicialized = true;
		}
	}

}
