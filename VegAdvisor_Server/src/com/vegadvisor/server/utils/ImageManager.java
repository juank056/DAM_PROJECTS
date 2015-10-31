/**
 * 
 */
package com.vegadvisor.server.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Clase que se encarga del almacenamiento de imagenes dentro del servidor
 * 
 * @author JuanCamilo
 *
 */
public class ImageManager {

	/**
	 * Modo de operaci�n:<br/>
	 * 1: Almacenamiento en disco del servidor<br/>
	 * 2: S3 Amazon
	 */
	private int operationMode;

	/**
	 * 
	 */
	public ImageManager() {
		// Modo de operaci�n en 1 por defecto
		this.operationMode = 1;
		// Busca operation mode parametro
		try {
			this.operationMode = Integer.valueOf(ConfigApplicationManager
					.getParameter("com.vegadvisor.server.image.opmode"));
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
	public void saveFileInServer(InputStream fileStream, String fileLocation)
			throws Exception {
		// Actua de acuerdo al modo de operaci�n
		switch (operationMode) {
		case 1: /* Modo regular */
			this.saveFileInServerOpmode1(fileStream, fileLocation);
			break;
		case 2: /* Amazon web services */
			this.saveFileInServerOpmode2(fileStream, fileLocation);
			break;
		}
	}

	/**
	 * Obtiene bytes del archivo de acuerdo a su path
	 * 
	 * @param filePath
	 *            Path del archivo
	 * @return Bytes del archivo
	 */
	public InputStream getImageFile(String filePath) {
		// Actua de acuerdo al modo de operaci�n
		switch (operationMode) {
		case 1: /* Modo regular */
			return this.getImageFileOpmode1(filePath);
		case 2: /* Amazon web services */
			return this.getImageFileOpmode2(filePath);
		}
		// Ningun caso (nunca ocurrir�)
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
	 */
	private InputStream getImageFileOpmode1(String filePath) {
		return ClassLoader.getSystemResourceAsStream(filePath);
	}

	/**
	 * Obtiene bytes de un archivo de acuerdo a su path (Amazon S3)
	 * 
	 * @param filePath
	 *            ruta del archivo
	 * @return Bytes del archivo
	 */
	private InputStream getImageFileOpmode2(String filePath) {
		// TODO IMPLEMENTAR
		return null;
	}

	/**
	 * Guarda Imagen en servidor
	 * 
	 * @param fileStream
	 *            Bytes del arhcivo
	 * @param fileLocation
	 *            ubicaci�n del archivo (fullpath)
	 * @throws IOException
	 *             Error guardando archivo
	 */
	private void saveFileInServerOpmode1(InputStream fileStream,
			String fileLocation) throws IOException {
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
	 *            ubicaci�n del archivo (fullpath)
	 * @throws IOException
	 *             Error guardando archivo
	 */
	private void saveFileInServerOpmode2(InputStream fileStream,
			String fileLocation) throws IOException {
		// TODO implementar c�digo amazon
	}

}