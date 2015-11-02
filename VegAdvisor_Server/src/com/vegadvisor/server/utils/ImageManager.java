/**
 * 
 */
package com.vegadvisor.server.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.sun.jersey.core.header.FormDataContentDisposition;

/**
 * Clase que se encarga del almacenamiento de imagenes dentro del servidor
 * 
 * @author JuanCamilo
 *
 */
public class ImageManager {

	/**
	 * Modo de operación:<br/>
	 * 1: Almacenamiento en disco del servidor<br/>
	 * 2: S3 Amazon
	 */
	private int operationMode;

	/**
	 * Amazon user key
	 */
	private String aws_userKey;

	/**
	 * Amazon password
	 */
	private String aws_password;

	/**
	 * Bucket de S3
	 */
	private String aws_bucket;

	/**
	 * 
	 */
	public ImageManager() {
		// Modo de operación en 1 por defecto
		this.operationMode = 1;
		// Busca operation mode parametro
		try {
			this.operationMode = Integer.valueOf(ConfigApplicationManager
					.getParameter("com.vegadvisor.server.image.opmode"));
			// Si es 2 obtiene parametros de amazon
			if (this.operationMode == 2) {
				this.aws_userKey = ConfigApplicationManager
						.getParameter("com.vegadvisor.server.image.awsuser");
				this.aws_password = ConfigApplicationManager
						.getParameter("com.vegadvisor.server.image.awspasswd");
				this.aws_bucket = ConfigApplicationManager
						.getParameter("com.vegadvisor.server.image.awsbucket");
			}
		} catch (Exception e) {/* Ocurrio error */
			// imprime pero ignora
			LogLogger.getInstance(getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
	}

	/**
	 * Guarda Imagen
	 * 
	 * @param fileStream
	 * @param fileLocation
	 * @throws IOException
	 */
	public void saveFileInServer(InputStream fileStream,
			FormDataContentDisposition fileDetail, String fileLocation)
			throws Exception {
		// Actua de acuerdo al modo de operación
		switch (operationMode) {
		case 1: /* Modo regular */
			this.saveFileInServerOpmode1(fileStream, fileDetail, fileLocation);
			break;
		case 2: /* Amazon web services */
			this.saveFileInServerOpmode2(fileStream, fileDetail, fileLocation);
			break;
		}
	}

	/**
	 * Obtiene bytes del archivo de acuerdo a su path
	 * 
	 * @param filePath
	 *            Path del archivo
	 * @return Bytes del archivo
	 * @throws IOException
	 */
	public InputStream getImageFile(String filePath) throws IOException {
		// Actua de acuerdo al modo de operación
		switch (operationMode) {
		case 1: /* Modo regular */
			return this.getImageFileOpmode1(filePath);
		case 2: /* Amazon web services */
			return this.getImageFileOpmode2(filePath);
		}
		// Ningun caso (nunca ocurrirá)
		return this.getImageFileOpmode1(filePath);
	}

	/**
	 * Obtiene la extension de un archivos
	 * 
	 * @param fileName
	 *            Nombre del archivo
	 * @return Extension que tiene el archivo
	 */
	public String getFileExtension(String fileName) {
		if (fileName == null)/* Por si es null */
			return Constants.BLANKS;
		int index = 0;
		index = fileName.lastIndexOf(Constants.DOT);
		if (index > 1)
			return fileName.substring(index);
		// Por si acaso no hay
		return Constants.BLANKS;
	}

	/**
	 * Obtiene bytes de un archivo de acuerdo a su path
	 * 
	 * @param filePath
	 *            ruta del archivo
	 * @return Bytes del archivo
	 * @throws FileNotFoundException
	 *             Error de archivo no encontrado
	 */
	private InputStream getImageFileOpmode1(String filePath)
			throws FileNotFoundException {
		return new FileInputStream(new File(filePath));
	}

	/**
	 * Obtiene bytes de un archivo de acuerdo a su path (Amazon S3)
	 * 
	 * @param filePath
	 *            ruta del archivo
	 * @return Bytes del archivo
	 */
	private InputStream getImageFileOpmode2(String filePath) {
		// Crea cliente de s3
		AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(
				aws_userKey, aws_password));
		// Obtiene objeto
		S3Object object = s3client.getObject(new GetObjectRequest(aws_bucket,
				filePath));
		// Retorna contenido
		return object.getObjectContent();
	}

	/**
	 * Guarda Imagen en servidor
	 * 
	 * @param fileStream
	 *            Bytes del arhcivo
	 * @param fileLocation
	 *            ubicación del archivo (fullpath)
	 * @throws IOException
	 *             Error guardando archivo
	 */
	private void saveFileInServerOpmode1(InputStream fileStream,
			FormDataContentDisposition fileDetail, String fileLocation)
			throws IOException {
		// Bytes leidos
		int read = 0;
		// Buffer para escritura
		byte[] bytes = new byte[1024];
		// Crea output stream
		OutputStream out = new FileOutputStream(new File(fileLocation));
		// Lee del input stream mientras hayan datos
		while ((read = fileStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		// Finaliza
		out.flush();
		out.close();
	}

	/**
	 * Guarda Imagen en servidor S3 de Amazon
	 * 
	 * @param fileStream
	 *            Bytes del arhcivo
	 * @param fileLocation
	 *            ubicación del archivo (fullpath)
	 * @throws IOException
	 *             Error guardando archivo
	 */
	private void saveFileInServerOpmode2(InputStream fileStream,
			FormDataContentDisposition fileDetail, String fileLocation)
			throws Exception {
		// Ejecuta modo de operacion 1 para guardar temporalmente en el servidor
		this.saveFileInServerOpmode1(fileStream, fileDetail, fileLocation);
		// Crea cliente de s3
		AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(
				aws_userKey, aws_password));
		// File a enviar
		File file = new File(fileLocation);
		// Pone objeto en s3
		s3client.putObject(new PutObjectRequest(aws_bucket, fileLocation, file));
		// Borra archivo del disco local
		file.delete();
	}
}
