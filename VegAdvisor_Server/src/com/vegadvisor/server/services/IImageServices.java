/**
 * 
 */
package com.vegadvisor.server.services;

import java.io.InputStream;
import java.util.Date;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.vegadvisor.server.services.bo.ReturnValidation;

/**
 * @author JuanCamilo
 *
 */
public interface IImageServices {

	/**
	 * M�todo para subir la imagen de un usuario
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param fileStream
	 *            Bytes del archivo
	 * @param fileDetail
	 *            detalle del archivo
	 * @return Retorno de validaci�n
	 */
	public ReturnValidation uploadUserImage(String userId,
			InputStream fileStream, FormDataContentDisposition fileDetail);

	/**
	 * M�todo para subir una imagen de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param fileStream
	 *            Bytes de la imagen
	 * @param fileDetail
	 *            Detalles de la imagen
	 * @return Retorno de validaci�n
	 */
	public ReturnValidation uploadEstablishmentImage(int establishmentId,
			InputStream fileStream, FormDataContentDisposition fileDetail);

	/**
	 * M�todo para subir la im�gen de una opinion de un establecimiento
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param opinionDate
	 *            Fecha de la opini�n
	 * @param opinionSecuence
	 *            Secuencia de la opini�n
	 * @param fileStream
	 *            Contenido de la imagen
	 * @param fileDetail
	 *            Detalle de la imagen
	 * @return Retorno de validaci�n
	 */
	public ReturnValidation uploadOpinionImage(int establishmentId,
			Date opinionDate, int opinionSecuence, InputStream fileStream,
			FormDataContentDisposition fileDetail);

	/**
	 * M�todo para subir la im�gen de un evento
	 * 
	 * @param countryCode
	 *            C�digo de pais
	 * @param cityCode
	 *            C�digo de ciudad
	 * @param eventDate
	 *            Fecha del evento
	 * @param eventSecuence
	 *            Secuencia del evento
	 * @param fileStream
	 *            Bytes de la imagen
	 * @param fileDetail
	 *            Detalle de la imagen
	 * @return Retorno de validaci�n
	 */
	public ReturnValidation uploadEventImage(String countryCode,
			String cityCode, Date eventDate, int eventSecuence,
			InputStream fileStream, FormDataContentDisposition fileDetail);

	/**
	 * M�todo para descargar una imagen
	 * 
	 * @param imagePath
	 *            Ruta de la im�gen
	 * @return Bytes de la imagen a descargar
	 */
	public InputStream downloadImage(String imagePath);

}
