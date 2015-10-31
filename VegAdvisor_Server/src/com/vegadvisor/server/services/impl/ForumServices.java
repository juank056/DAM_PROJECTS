/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Fodreshi;
import com.vegadvisor.server.persistence.bo.FodreshiId;
import com.vegadvisor.server.persistence.bo.Fomhilfo;
import com.vegadvisor.server.persistence.dao.IFodreshiDAO;
import com.vegadvisor.server.persistence.dao.IFomhilfoDAO;
import com.vegadvisor.server.services.IForumServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.MessageBundle;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios de foro
 * 
 * @author JuanCamilo
 *
 */
public class ForumServices implements IForumServices {

	/**
	 * DAO para trabajar con tabla FOMHILFO
	 */
	private IFomhilfoDAO fomhilfoDao;

	/**
	 * DAO para trabajar con la tabla FODRESHI
	 */
	private IFodreshiDAO fodreshiDao;

	/**
	 * Indicador de Daos iniciados
	 */
	private boolean daosInicialized;

	/**
	 * Constructor
	 */
	public ForumServices() {
		// Daos iniciados en false
		this.daosInicialized = false;
	}

	/**
	 * Método para buscar los hilos de foro de acuerdo a una pista
	 * 
	 * @param clue
	 *            pista de búsqueda para los hilos de foro
	 * @return Lista de hilos de foro
	 */
	@Override
	public List<Fomhilfo> findForumThreads(String clue) {
		// Retorno
		List<Fomhilfo> response = new ArrayList<Fomhilfo>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca hilos de foro
			response = fomhilfoDao.findByName(clue);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Método para iniciar un nuevo hilo de foro
	 * 
	 * @param userId
	 *            Id del usuario que registra el hilo
	 * @param threadTitle
	 *            titulo del hilo de foro
	 * @param threadDetail
	 *            Detalle del hilo
	 * @return Retorno de validación
	 */
	@Override
	public ReturnValidation createForumThread(String userId,
			String threadTitle, String threadDetail) {
		try {
			// Inicia DAOS
			initDaos();
			// Crea registro FOMHILFO
			Fomhilfo hilfo = new Fomhilfo();
			// Asigna atributos
			// Usuario
			hilfo.setUsucusuak(userId);
			// Fecha registro
			hilfo.setHiffregff(DateUtils.getCurrentUtilDate());
			// hora registro
			hilfo.setHifhoratf(DateUtils.getCurrentUtilDate());
			// Titulo del foro
			hilfo.setHiftituaf(threadTitle);
			// Detalle del foro
			hilfo.setHifdetaaf(threadDetail);
			// Fecha ultima respuesta
			hilfo.setHiffecuff(Constants.DEFAULT_DATE);
			// Guarda registro en la base de datos
			fomhilfoDao.save(hilfo);
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
	 * Método para registrar la respuesta de un hilo de foro
	 * 
	 * @param userId
	 *            Id del usuario que registra la respuesta
	 * @param threadId
	 *            id del hilo que se esta respondiendo
	 * @param responseDetail
	 *            Detalle de la respuesta
	 * @return Retorno de validación
	 */
	@Override
	public ReturnValidation createForumThreadResponse(String userId,
			int threadId, String responseDetail) {
		try {
			// Inicia DAOS
			initDaos();
			// Crea registro FODRESHI
			Fodreshi reshi = new Fodreshi();
			// Llave del registro
			FodreshiId id = new FodreshiId(threadId, 0);
			// Asigna llave
			reshi.setId(id);
			// Atributos adicionales
			// Usuario de respuesta
			reshi.setUsucusuak(userId);
			// Fecha registro
			reshi.setRhffregff(DateUtils.getCurrentUtilDate());
			// Hora de registro
			reshi.setRhfhoratf(DateUtils.getCurrentUtilDate());
			// Detalle de la respuesta
			reshi.setRhfdetaaf(responseDetail);
			// Guarda registro en la base de datos
			fodreshiDao.save(reshi);
			// Obtiene hilo padre
			Fomhilfo hilfo = fomhilfoDao.findById(threadId);
			// Actualiza fecha de ultima respuesta
			hilfo.setHiffecuff(DateUtils.getCurrentUtilDate());
			// Actualiza registro en la base de datos
			fomhilfoDao.update(hilfo);
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
	 * Inicia los DAos si no han sido iniciados
	 */
	private void initDaos() {
		// Inicia DAOs si no han sido iniciados
		if (!daosInicialized) {
			fomhilfoDao = SpringAppContext.getAppContext().getBean(
					IFomhilfoDAO.class);
			fodreshiDao = SpringAppContext.getAppContext().getBean(
					IFodreshiDAO.class);
			// Daos iniciados a true
			daosInicialized = true;
		}
	}

}
