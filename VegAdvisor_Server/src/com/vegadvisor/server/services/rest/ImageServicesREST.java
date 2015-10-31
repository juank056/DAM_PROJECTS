/**
 * 
 */
package com.vegadvisor.server.services.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.vegadvisor.server.services.IImageServices;
import com.vegadvisor.server.services.bo.ReturnValidation;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Servicios para envio y recepción de imágenes
 * 
 * @author JuanCamilo
 *
 */
@Path("/images")
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

	@POST
	@Path("/uploadUserImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnValidation uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("name") String name,
			@FormDataParam("desc") String desc) {
		System.out.println("NAME: " + name);
		System.out.println("DESC: " + desc);
		String uploadedFileLocation = "c:/temp/upload/" + name
				+ fileDetail.getFileName();
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;
		return new ReturnValidation("1", output);
	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

		try {
			int read = 0;
			byte[] bytes = new byte[1024];
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
