/**
 * 
 */
package com.vegadvisor.server.services.rest.bo;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase de retorno de validación
 * 
 * @author JuanCamilo
 *
 */
public class ReturnValidation {

	/**
	 * Indicador de validación
	 */
	private String validationInd;

	/**
	 * Mensaje de validación
	 */
	private String message;

	/**
	 * Mapa de parámetros
	 */
	private Map<String, String> params;

	/**
	 * Constructor sin parametros
	 */
	public ReturnValidation() {
		// Inicia Mapa
		this.params = new HashMap<String, String>();
	}

	/**
	 * Constructor con parametros
	 * 
	 * @param validationInd
	 *            Indicador de validacion
	 * @param message
	 *            Mensaje de validación
	 */
	public ReturnValidation(String validationInd, String message) {
		super();
		this.validationInd = validationInd;
		this.message = message;
		// Inicia Mapa
		this.params = new HashMap<String, String>();
	}

	/**
	 * @return the validationInd
	 */
	public String getValidationInd() {
		return validationInd;
	}

	/**
	 * @param validationInd
	 *            the validationInd to set
	 */
	public void setValidationInd(String validationInd) {
		this.validationInd = validationInd;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the params
	 */
	public Map<String, String> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
