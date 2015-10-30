/**
 * 
 */
package com.vegadvisor.server.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.bo.Usmusuar;
import com.vegadvisor.server.persistence.dao.IUsdcheusDAO;
import com.vegadvisor.server.persistence.dao.IUsmusuarDAO;
import com.vegadvisor.server.services.IUserServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.MessageBundle;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * @author JuanCamilo
 *
 */
public class UserServices implements IUserServices {

	/**
	 * DAO para trabajar con tabla USMUSUAR
	 */
	private IUsmusuarDAO usmusuarDao;

	/**
	 * DAO para trabajar con la tabla USDCHEUS
	 */
	private IUsdcheusDAO usdcheusDao;

	/**
	 * Constructor de servicio
	 */
	public UserServices() {
	}

	/**
	 * Método para validar un usuario y su contraseña
	 * 
	 * @param userId
	 *            Id de usuario
	 * @param password
	 *            Contraseña
	 * @return String[0] = Indicador de Validación (0,1).<br/>
	 *         String[1] = Mensaje de validacion <br/>
	 *         String[2] = Nombre del usuario<br/>
	 *         String[3] = Pais del usuario<br/>
	 *         String[4] = Ciudad del usuario<br/>
	 */
	@Override
	public ReturnValidation validateUser(String userId, String password) {

		try {
			// Inicia DAOS
			initDaos();
			// Busca el usuario en la base de datos
			Usmusuar usuar = usmusuarDao.findById(userId);
			// Revisa si el usuario se ha encontrado
			if (usuar == null) {/* Usuario no encontrado */
				// Retorna con error
				return new ReturnValidation(Constants.ZERO,
						MessageBundle.getMessage("com.vegadvisor.user.msj001"));
			}

			// Validamos la contraseña del usuario
			if (!usuar.getUsupassaf().equals(password)) {
				// Retorna con error
				return new ReturnValidation(Constants.ZERO,
						MessageBundle.getMessage("com.vegadvisor.user.msj001"));
			}
			// Retorna ok
			ReturnValidation response = new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.user.msj002"));
			// Nombre del usuario
			response.getParams().put(
					Constants.USER_NAME,
					usuar.getUsunusuaf() + Constants.BLANK_SPACE
							+ usuar.getUsuapelaf());
			// Pais del usuario
			response.getParams().put(Constants.USER_COUNTRY,
					usuar.getPaicpaiak());
			// Ciudad del usuario
			response.getParams().put(Constants.USER_CITY, usuar.getCiucciuak());
			return response;
		} catch (Exception e) {/* Ha ocurrido un error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IUserServices#createUser(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ReturnValidation createUser(String userId, String userName,
			String userLastName, String email, String password) {
		try {
			// Inicia DAOS
			initDaos();
			// Buscamos usuario por id
			Usmusuar usuar = usmusuarDao.findById(userId);
			if (usuar != null) {/* Usuario ya registrado */
				// Retornamos error
				return new ReturnValidation(Constants.ZERO,
						MessageBundle.getMessage("com.vegadvisor.user.msj003"));
			}
			// Buscar el usuario por email
			List<Usmusuar> usuars = usmusuarDao.findByEmail(email);
			if (usuars.size() > 0) {
				// Retornamos error
				return new ReturnValidation(Constants.ZERO,
						MessageBundle.getMessage("com.vegadvisor.user.msj004"));
			}
			// Creamos nuevo registro de usuario
			usuar = new Usmusuar();
			// Asignamos parametros
			// Id Usuario
			usuar.setUsucusuak(userId);
			// Email
			usuar.setUsuemaiaf(email);
			// Nombre
			usuar.setUsunusuaf(userName);
			// Apellido
			usuar.setUsuapelaf(userLastName);
			// Contraseña
			usuar.setUsupassaf(password);
			// Fecha de nacimiento
			usuar.setUsufnacff(Constants.DEFAULT_DATE);
			// Genero
			usuar.setUsugenpvf(Constants.ZERO);
			// Ruta foto
			usuar.setUsufotoaf(Constants.BLANKS);
			// Es vegano
			usuar.setUsuvegasf(Constants.ZERO);
			// Aficiones
			usuar.setUsuaficaf(Constants.BLANKS);
			// Pais
			usuar.setPaicpaiak(Constants.BLANKS);
			// Ciudad
			usuar.setCiucciuak(Constants.BLANKS);
			// Activo chat si
			usuar.setUsuiactsf(Constants.ONE);
			// Guardamos registro de usuario
			usmusuarDao.save(usuar);
			// Retorno
			return new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.user.msj005"));
		} catch (DAOException e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IUserServices#updateUser(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.util.Date, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ReturnValidation updateUser(String userId, String userName,
			String userLastName, String email, String password,
			Date dateOfBirth, String countryCode, String cityCode,
			String isVegan, String hobbies) {
		// Inicia DAOS
		initDaos();
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vegadvisor.server.services.IUserServices#checkInUser(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public ReturnValidation checkInUser(String userId, String establishmentId) {
		// TODO Auto-generated method stub
		// Inicia DAOS
		initDaos();
		return null;
	}

	/**
	 * Inicia los DAos si no han sido iniciados
	 */
	private void initDaos() {
		// Inicia DAOs
		if (usmusuarDao == null)
			usmusuarDao = SpringAppContext.getAppContext().getBean(
					IUsmusuarDAO.class);
		if (usdcheusDao == null)
			usdcheusDao = SpringAppContext.getAppContext().getBean(
					IUsdcheusDAO.class);
	}

}
