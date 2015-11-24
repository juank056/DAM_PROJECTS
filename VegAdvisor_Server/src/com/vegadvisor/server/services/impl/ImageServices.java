package com.vegadvisor.server.services.impl;

import java.io.InputStream;
import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.vegadvisor.server.persistence.bo.Esdimaes;
import com.vegadvisor.server.persistence.bo.EsdimaesId;
import com.vegadvisor.server.persistence.bo.Esdimope;
import com.vegadvisor.server.persistence.bo.EsdimopeId;
import com.vegadvisor.server.persistence.bo.Evdimaev;
import com.vegadvisor.server.persistence.bo.EvdimaevId;
import com.vegadvisor.server.persistence.bo.Usmusuar;
import com.vegadvisor.server.persistence.dao.IEsdimaesDAO;
import com.vegadvisor.server.persistence.dao.IEsdimopeDAO;
import com.vegadvisor.server.persistence.dao.IEvdimaevDAO;
import com.vegadvisor.server.persistence.dao.IUsmusuarDAO;
import com.vegadvisor.server.services.IImageServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.ConfigApplicationManager;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.ImageManager;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.MessageBundle;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios de imagenes
 * 
 * @author JuanCamilo
 *
 */
public class ImageServices implements IImageServices {

	/**
	 * DAO para trabajar con tabla USMUSUAR
	 */
	private IUsmusuarDAO usmusuarDao;

	/**
	 * DAO para trabajar con la tabla ESDIMAES
	 */
	private IEsdimaesDAO esdimaesDao;

	/**
	 * DAO para trabajar con la tabla ESDIMOPE
	 */
	private IEsdimopeDAO esdimopeDao;

	/**
	 * DAO para trabajar con la tabla EVDIMAEV
	 */
	private IEvdimaevDAO evdimaevDao;

	/**
	 * Indicador de Daos iniciados
	 */
	private boolean daosInicialized;

	/**
	 * Encargado de guardar y cargar las imagenes
	 */
	private ImageManager imageManager;

	/**
	 * Constructor
	 */
	public ImageServices() {
		// Daos iniciados en false
		this.daosInicialized = false;
		// Inicia manager de imagenes
		this.imageManager = new ImageManager();
	}

	/**
	 * Método para subir la imagen de un usuario
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param fileStream
	 *            Bytes del archivo
	 * @param fileDetail
	 *            detalle del archivo
	 * @return Retorno de validación
	 */
	public ReturnValidation uploadUserImage(String userId,
			InputStream fileStream, FormDataContentDisposition fileDetail) {
		try {
			// Inicia DAOS
			initDaos();
			// Obtiene registro de usuario
			Usmusuar usuar = usmusuarDao.findById(userId);
			// Genera path del archivo
			String filePath = ConfigApplicationManager
					.getParameter("com.vegadvisor.server.image.basepath");
			// Concatena id del usuario y extension
			filePath += Constants.USER_IMAGE;
			// Id del usuario
			filePath += userId;
			// Fecha hora
			filePath += DateUtils.getCurrentDateTime().replace(":", "")
					.replace("-", "");
			// Extension del archivo
			filePath += imageManager.getFileExtension(fileDetail.getFileName());
			// Quita espacios en blanco
			filePath = filePath
					.replace(Constants.BLANK_SPACE, Constants.BLANKS);
			// Guarda imagen en el servidor de imagenes
			imageManager.saveFileInServer(fileStream, fileDetail, filePath);
			// Actualiza path de la imagen del usuario
			usuar.setUsufotoaf(filePath);
			// Actualiza base de datos
			usmusuarDao.update(usuar);
			// Retorno
			return new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.util.msj002"));
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/**
	 * Método para subir una imagen de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param fileStream
	 *            Bytes de la imagen
	 * @param fileDetail
	 *            Detalles de la imagen
	 * @return Retorno de validación
	 */
	public ReturnValidation uploadEstablishmentImage(int establishmentId,
			InputStream fileStream, FormDataContentDisposition fileDetail) {
		try {
			// Inicia DAOS
			initDaos();
			// Genera path del archivo
			String filePath = ConfigApplicationManager
					.getParameter("com.vegadvisor.server.image.basepath");
			// Concatena id del establecimiento y extension
			filePath += Constants.ESTABLISHMENT_IMAGE;
			// Id del establecimiento
			filePath += establishmentId;
			// Time de subida de imagen
			filePath += Constants.DASH
					+ DateUtils.getDateTimeString(
							DateUtils.getCurrentUtilDate(), Constants.MINUS);
			// Extension del archivo
			filePath += imageManager.getFileExtension(fileDetail.getFileName());
			// Quita espacios en blanco
			filePath = filePath
					.replace(Constants.BLANK_SPACE, Constants.BLANKS);
			// Guarda imagen en el servidor de imagenes
			imageManager.saveFileInServer(fileStream, fileDetail, filePath);
			// Crea registro ESDIMAES
			Esdimaes imaes = new Esdimaes();
			// Id del registro
			EsdimaesId id = new EsdimaesId(establishmentId, 0);
			// Datos adicionales
			imaes.setId(id);
			imaes.setIesrimaaf(filePath);
			// Guarda registro en la base de datos
			esdimaesDao.save(imaes);
			// Retorno
			return new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.util.msj002"));
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/**
	 * Método para subir la imágen de una opinion de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param opinionDate
	 *            Fecha de la opinión
	 * @param opinionSecuence
	 *            Secuencia de la opinión
	 * @param fileStream
	 *            Contenido de la imagen
	 * @param fileDetail
	 *            Detalle de la imagen
	 * @return Retorno de validación
	 */
	public ReturnValidation uploadOpinionImage(int establishmentId,
			Date opinionDate, int opinionSecuence, InputStream fileStream,
			FormDataContentDisposition fileDetail) {
		try {
			// Inicia DAOS
			initDaos();
			// Genera path del archivo
			String filePath = ConfigApplicationManager
					.getParameter("com.vegadvisor.server.image.basepath");
			// Concatena id de la opinion y extension
			filePath += Constants.OPINION_IMAGE;
			// Id de la opinion
			filePath += establishmentId;
			filePath += DateUtils.getDateStringYYYYMMDD(opinionDate);
			filePath += opinionSecuence;
			// Time de subida de imagen
			filePath += Constants.DASH
					+ DateUtils.getDateTimeString(
							DateUtils.getCurrentUtilDate(), Constants.MINUS);
			// Extension del archivo
			filePath += imageManager.getFileExtension(fileDetail.getFileName());
			// Quita espacios en blanco
			filePath = filePath
					.replace(Constants.BLANK_SPACE, Constants.BLANKS);
			// Guarda imagen en el servidor de imagenes
			imageManager.saveFileInServer(fileStream, fileDetail, filePath);
			// Crea registro ESDIMOPE
			Esdimope imope = new Esdimope();
			// Id del registro
			EsdimopeId id = new EsdimopeId(establishmentId, opinionDate,
					opinionSecuence, 0);
			// Datos adicionales
			imope.setId(id);
			imope.setIoerimaaf(filePath);
			// Guarda registro en la base de datos
			esdimopeDao.save(imope);
			// Retorno
			return new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.util.msj002"));
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/**
	 * Método para subir la imágen de un evento
	 * 
	 * @param countryCode
	 *            Código de pais
	 * @param cityCode
	 *            Código de ciudad
	 * @param eventDate
	 *            Fecha del evento
	 * @param eventSecuence
	 *            Secuencia del evento
	 * @param fileStream
	 *            Bytes de la imagen
	 * @param fileDetail
	 *            Detalle de la imagen
	 * @return Retorno de validación
	 */
	public ReturnValidation uploadEventImage(String countryCode,
			String cityCode, Date eventDate, int eventSecuence,
			InputStream fileStream, FormDataContentDisposition fileDetail) {
		try {
			// Inicia DAOS
			initDaos();
			// Genera path del archivo
			String filePath = ConfigApplicationManager
					.getParameter("com.vegadvisor.server.image.basepath");
			// Concatena id del evento y extension
			filePath += Constants.OPINION_IMAGE;
			// Id del evento
			filePath += countryCode;
			filePath += cityCode;
			filePath += DateUtils.getDateStringYYYYMMDD(eventDate);
			filePath += eventSecuence;
			// Time de subida de imagen
			filePath += Constants.DASH
					+ DateUtils.getDateTimeString(
							DateUtils.getCurrentUtilDate(), Constants.MINUS);
			// Extension del archivo
			filePath += imageManager.getFileExtension(fileDetail.getFileName());
			// Quita espacios en blanco
			filePath = filePath
					.replace(Constants.BLANK_SPACE, Constants.BLANKS);
			// Guarda imagen en el servidor de imagenes
			imageManager.saveFileInServer(fileStream, fileDetail, filePath);
			// Crea registro EVDIMAEV
			Evdimaev imaev = new Evdimaev();
			// Id del registro
			EvdimaevId id = new EvdimaevId(countryCode, cityCode, eventDate,
					eventSecuence, 0);
			// Datos adicionales
			imaev.setId(id);
			imaev.setImerimaaf(filePath);
			// Guarda registro en la base de datos
			evdimaevDao.save(imaev);
			// Retorno
			return new ReturnValidation(Constants.ONE,
					MessageBundle.getMessage("com.vegadvisor.util.msj002"));
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
			return new ReturnValidation(Constants.ZERO,
					MessageBundle.getMessage("com.vegadvisor.util.apperror"));
		}
	}

	/**
	 * Método para descargar una imagen
	 * 
	 * @param imagePath
	 *            Ruta de la imágen
	 * @return Bytes de la imagen a descargar
	 */
	public InputStream downloadImage(String imagePath) {
		InputStream image = null;
		try {
			// Busca imagen en servidor
			image = imageManager.getImageFile(imagePath);
		} catch (Exception e) {/* Ha ocurrido algn error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
		// Retorna
		return image;
	}

	/**
	 * Inicia los DAos si no han sido iniciados
	 */
	private void initDaos() {
		// Inicia DAOs si no han sido iniciados
		if (!daosInicialized) {
			usmusuarDao = SpringAppContext.getAppContext().getBean(
					IUsmusuarDAO.class);
			esdimaesDao = SpringAppContext.getAppContext().getBean(
					IEsdimaesDAO.class);
			esdimopeDao = SpringAppContext.getAppContext().getBean(
					IEsdimopeDAO.class);
			evdimaevDao = SpringAppContext.getAppContext().getBean(
					IEvdimaevDAO.class);
			// Daos iniciados a true
			daosInicialized = true;
		}
	}

}
