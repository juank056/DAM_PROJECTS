/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Chdmensa;
import com.vegadvisor.server.persistence.bo.ChdmensaId;
import com.vegadvisor.server.persistence.bo.Usmusuar;
import com.vegadvisor.server.persistence.dao.IChdmensaDAO;
import com.vegadvisor.server.persistence.dao.IUsmusuarDAO;
import com.vegadvisor.server.services.IChatServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.MessageBundle;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Clase que define los servicios de Chat
 * 
 * @author JuanCamilo
 *
 */
public class ChatServices implements IChatServices {

	/**
	 * DAO para trabajar con tabla USMUSUAR
	 */
	private IUsmusuarDAO usmusuarDao;

	/**
	 * DAO para trabajar con la tabla CHDMENSA
	 */
	private IChdmensaDAO chdmensaDao;

	/**
	 * Indicador de Daos iniciados
	 */
	private boolean daosInicialized;

	/**
	 * Constructor
	 */
	public ChatServices() {
		// Daos iniciados en false
		this.daosInicialized = false;
	}

	/**
	 * Obtiene los usuarios activos de chat segun pista de su id
	 * 
	 * @param clue
	 *            Pista para encontrar usuario
	 * @return Lista de usuarios que estan en el chat
	 */
	@Override
	public List<Usmusuar> findChatUsers(String clue) {
		// Retorno
		List<Usmusuar> response = new ArrayList<Usmusuar>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca usuarios de acuerdo clue y activos para chat
			response = usmusuarDao.findByNameAndActive(clue);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return response;
	}

	/**
	 * Método para registrar un mensaje de chat dentro del sistema
	 * 
	 * @param userIdFrom
	 *            Usuario que envia el mensaje
	 * @param userIdTo
	 *            Usuario que recibirá el mensaje
	 * @param content
	 *            Contenido del mensaje
	 * @return Indicador de registro de mensaje ok
	 */
	@Override
	public ReturnValidation registerChatMessage(String userIdFrom,
			String userIdTo, String content) {
		try {
			// Inicia DAOS
			initDaos();
			// Crea registro de CHDMENSA
			Chdmensa mensa = new Chdmensa();
			// Llave del registro
			ChdmensaId id = new ChdmensaId(userIdFrom, userIdTo,
					DateUtils.getCurrentUtilDate(), 0);
			// Asigna llave
			mensa.setId(id);
			// Indicador de entregado 'no'
			mensa.setMchientsf(Constants.ZERO);
			// Contenido del mensaje
			mensa.setMchmensaf(content);
			// Guarda registro
			chdmensaDao.save(mensa);
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
	 * Método para recolectar los mensajes de chat pendientes
	 * 
	 * @param userId
	 *            Id del usuario que va a recolectar sus mensajes
	 * @return Lista de mensajes de chat pendientes por recoger
	 */
	@Override
	public List<Chdmensa> recolectChatMessages(String userId) {
		// Retorno
		List<Chdmensa> response = new ArrayList<Chdmensa>();
		try {
			// Inicia DAOS
			initDaos();
			// Busca mensajes pendientes para el usuario
			response = chdmensaDao.findUserPendingMessages(userId);
			// Marca mensajes como entregados
			for (Chdmensa mensa : response) {
				// Actualiza registro a entregado
				mensa.setMchientsf(Constants.ONE);
				// Actualiza en la base de datos
				chdmensaDao.update(mensa);
			}
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
			usmusuarDao = SpringAppContext.getAppContext().getBean(
					IUsmusuarDAO.class);
			chdmensaDao = SpringAppContext.getAppContext().getBean(
					IChdmensaDAO.class);
			// Daos iniciados a true
			daosInicialized = true;
		}
	}

}
