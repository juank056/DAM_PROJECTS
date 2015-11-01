/**
 * 
 */
package com.vegadvisor.server.services.rest;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.vegadvisor.server.services.IImageServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.DateUtils;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios para envio y recepción de imágenes
 * 
 * @author JuanCamilo
 *
 */
@Path("/image")
public class ImageServicesREST {

	/**
	 * Servicios de imágenes
	 */
	private IImageServices imageServices;

	/**
	 * Constructor
	 */
	public ImageServicesREST() {
		// Inicia servicios de imágenes
		imageServices = SpringAppContext.getAppContext().getBean(
				IImageServices.class);
	}

	/**
	 * Sube una imagen de establecimiento al servidor
	 * 
	 * @param fileStream
	 *            Bytes de la imagen
	 * @param fileDetail
	 *            Detalle del archivo
	 * @param establishmentId
	 *            Id del establecimiento
	 * @return
	 */
	@POST
	@Path("/uploadEstablishmentImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation uploadEstablishmentImage(
			@FormDataParam("establishmentId") int establishmentId,
			@FormDataParam("image") InputStream fileStream,
			@FormDataParam("image") FormDataContentDisposition fileDetail) {
		LogLogger.getInstance(getClass()).logger(
				"Inicia uploadEstablishmentImage", LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = imageServices.uploadEstablishmentImage(
				establishmentId, fileStream, fileDetail);
		LogLogger.getInstance(getClass()).logger(
				"Finaliza uploadEstablishmentImage", LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Sube la imagen de un evento al servidor
	 * 
	 * @param countryCode
	 *            Código de pais del evento
	 * @param cityCode
	 *            Ciudad del evento
	 * @param eventDate
	 *            Fecha del evento
	 * @param eventSecuence
	 *            Secuencia del evento
	 * @param fileStream
	 *            Bytes del archivo
	 * @param fileDetail
	 *            Detalle del archivo
	 * @return Retorno de validación
	 */
	@POST
	@Path("/uploadEventImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation uploadEventImage(
			@FormDataParam("countryCode") String countryCode,
			@FormDataParam("cityCode") String cityCode,
			@FormDataParam("eventDate") String eventDate,
			@FormDataParam("eventSecuence") int eventSecuence,
			@FormDataParam("image") InputStream fileStream,
			@FormDataParam("image") FormDataContentDisposition fileDetail) {
		LogLogger.getInstance(getClass()).logger("Inicia uploadEventImage",
				LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = imageServices.uploadEventImage(countryCode,
				cityCode, DateUtils.getDateDateYYYYMMDD(eventDate),
				eventSecuence, fileStream, fileDetail);
		LogLogger.getInstance(getClass()).logger("Finaliza uploadEventImage",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Sube la imagen de una opinion al servidor
	 * 
	 * @param establishmentId
	 *            Id del establecimiento
	 * @param opinionDate
	 *            Fecha de la opinión
	 * @param opinionSecuence
	 *            secuencia de la opinión
	 * @param fileStream
	 *            Bytes de la imagen
	 * @param fileDetail
	 *            Detalle de la imagen
	 * @return Retorno de validación
	 */
	@POST
	@Path("/uploadOpinionImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation uploadOpinionImage(
			@FormDataParam("establishmentId") int establishmentId,
			@FormDataParam("opinionDate") String opinionDate,
			@FormDataParam("opinionSecuence") int opinionSecuence,
			@FormDataParam("image") InputStream fileStream,
			@FormDataParam("image") FormDataContentDisposition fileDetail) {
		LogLogger.getInstance(getClass()).logger("Inicia uploadOpinionImage",
				LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = imageServices.uploadOpinionImage(
				establishmentId, DateUtils.getDateDateYYYYMMDD(opinionDate),
				opinionSecuence, fileStream, fileDetail);
		LogLogger.getInstance(getClass()).logger("Finaliza uploadOpinionImage",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	/**
	 * Sube la imagen de un usuario al servidor
	 * 
	 * @param userId
	 *            Id del usuario
	 * @param fileStream
	 *            Bytes de la imagen
	 * @param fileDetail
	 *            detalle de la imagen
	 * @return Retorno de validación
	 */
	@POST
	@Path("/uploadUserImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation uploadUserImage(
			@FormDataParam("userId") String userId,
			@FormDataParam("image") InputStream fileStream,
			@FormDataParam("image") FormDataContentDisposition fileDetail) {
		LogLogger.getInstance(getClass()).logger("Inicia uploadUserImage",
				LogLogger.DEBUG);
		// Ejecuta servicio
		ReturnValidation response = imageServices.uploadUserImage(userId,
				fileStream, fileDetail);
		LogLogger.getInstance(getClass()).logger("Finaliza uploadUserImage",
				LogLogger.DEBUG);
		// Retornamos
		return response;
	}

	@POST
	@Path("/downloadImage")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON })
	@Produces({ "image/png", "image/jpeg", "image/gif" })
	public Response downloadImage(@FormParam("imagePath") String imagePath) {
		LogLogger.getInstance(getClass()).logger("Inicia downloadImage",
				LogLogger.DEBUG);
		InputStream response = null;
		// Ejecuta servicio
		response = imageServices.downloadImage(imagePath);
		LogLogger.getInstance(getClass()).logger("Finaliza downloadImage",
				LogLogger.DEBUG);
		// Retornamos
		return Response.ok(response).build();
	}

}
