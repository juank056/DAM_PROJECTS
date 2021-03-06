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
import com.vegadvisor.server.persistence.bo.Esmestab;
import com.vegadvisor.server.persistence.dao.IEsdopiesDAO;
import com.vegadvisor.server.persistence.dao.IEsmestabDAO;
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
	 * DAO para trabajar con tabla ESMESTAB
	 */
	private IEsmestabDAO esmestabDao;

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
			// Obtiene promedio de estrellas de las opiniones registradas
			double averageStars = esdopiesDao.calculateAverageStars(id
					.getEstcestnk());
			// Obtiene establecimiento
			Esmestab estab = esmestabDao.findById(establishmentId);
			// Actualiza promedio de estrellas
			estab.setEstpestnf(averageStars);
			// Actualiza registro
			esmestabDao.update(estab);
			// Retorno
			ReturnValidation response = new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.util.msj001"));
			// Asigna parametros de la llave de la opini�n
			response.getParams().put("estcestnk",
					Constants.BLANKS + opies.getId().getEstcestnk());
			response.getParams().put(
					"oesfregfk",
					Constants.BLANKS
							+ DateUtils.getDateStringYYYYMMDD(opies.getId()
									.getOesfregfk()));
			response.getParams().put("oescoesnk",
					Constants.BLANKS + opies.getId().getOescoesnk());
			// Retorno
			return response;
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
	 *            N�mero m�ximo de op�niones a traer
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
			esmestabDao = SpringAppContext.getAppContext().getBean(
					IEsmestabDAO.class);
			// Daos iniciados a true
			daosInicialized = true;
		}
	}

}
