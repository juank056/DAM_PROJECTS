package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.DAOException;
import com.vegadvisor.server.persistence.dao.IUsmusuarDAO;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Fodreshi generated by hbm2java
 */
public class Fodreshi implements java.io.Serializable, AbstractBO<FodreshiId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FodreshiId id;
	private String usucusuak;
	private Date rhffregff;
	private Date rhfhoratf;
	private String rhfdetaaf;
	// Usuario que registra la respuesta
	private String userName;

	public Fodreshi() {
	}

	public Fodreshi(FodreshiId id, String usucusuak, Date rhffregff,
			Date rhfhoratf, String rhfdetaaf) {
		this.id = id;
		this.usucusuak = usucusuak;
		this.rhffregff = rhffregff;
		this.rhfhoratf = rhfhoratf;
		this.rhfdetaaf = rhfdetaaf;
	}

	public FodreshiId getId() {
		return this.id;
	}

	public void setId(FodreshiId id) {
		this.id = id;
	}

	public String getUsucusuak() {
		return this.usucusuak;
	}

	public void setUsucusuak(String usucusuak) {
		this.usucusuak = usucusuak;
	}

	public Date getRhffregff() {
		return this.rhffregff;
	}

	public void setRhffregff(Date rhffregff) {
		this.rhffregff = rhffregff;
	}

	public Date getRhfhoratf() {
		return this.rhfhoratf;
	}

	public void setRhfhoratf(Date rhfhoratf) {
		this.rhfhoratf = rhfhoratf;
	}

	public String getRhfdetaaf() {
		return this.rhfdetaaf;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRhfdetaaf(String rhfdetaaf) {
		this.rhfdetaaf = rhfdetaaf;
	}

	@Override
	public FodreshiId getPrimaryKey() {
		return id;
	}

	@Override
	public void cleanObject() {
		try {
			// Inicia dao USMUSUAR
			IUsmusuarDAO usmusuarDao = SpringAppContext.getAppContext()
					.getBean(IUsmusuarDAO.class);
			// Obtiene nombre del usuario que registra
			Usmusuar usuar = usmusuarDao.findById(usucusuak);
			// Asigna nombre de usuario
			this.setUserName(usuar.getUsunusuaf() + Constants.BLANK_SPACE
					+ usuar.getUsuapelaf());
		} catch (DAOException e) {/* Ocurrio error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
	}

}
