package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.dao.IEsdimaesDAO;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

/**
 * Esmestab generated by hbm2java
 */
public class Esmestab implements java.io.Serializable, AbstractBO<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer estcestnk;
	private String usucusuak;
	private String estnestaf;
	private int tesctesnk;
	private String estdireaf;
	private String estteleaf;
	private Date esthoratf;
	private Date esthorctf;
	private String paicpaiak;
	private String ciucciuak;
	private double estlatinf;
	private double estlongnf;
	private String estdestaf;
	private double estpestnf;
	private String estiactsf;
	// Lista de imagenes del establecimiento
	private List<Esdimaes> images;

	public Esmestab() {
	}

	public Esmestab(String usucusuak, String estnestaf, int tesctesnk,
			String estdireaf, String estteleaf, Date esthoratf, Date esthorctf,
			String paicpaiak, String ciucciuak, double estlatinf,
			double estlongnf, String estdestaf, double estpestnf,
			String estiactsf) {
		this.usucusuak = usucusuak;
		this.estnestaf = estnestaf;
		this.tesctesnk = tesctesnk;
		this.estdireaf = estdireaf;
		this.estteleaf = estteleaf;
		this.esthoratf = esthoratf;
		this.esthorctf = esthorctf;
		this.paicpaiak = paicpaiak;
		this.ciucciuak = ciucciuak;
		this.estlatinf = estlatinf;
		this.estlongnf = estlongnf;
		this.estdestaf = estdestaf;
		this.estpestnf = estpestnf;
		this.estiactsf = estiactsf;
	}

	public Integer getEstcestnk() {
		return this.estcestnk;
	}

	public void setEstcestnk(Integer estcestnk) {
		this.estcestnk = estcestnk;
	}

	public String getUsucusuak() {
		return this.usucusuak;
	}

	public void setUsucusuak(String usucusuak) {
		this.usucusuak = usucusuak;
	}

	public String getEstnestaf() {
		return this.estnestaf;
	}

	public void setEstnestaf(String estnestaf) {
		this.estnestaf = estnestaf;
	}

	public int getTesctesnk() {
		return this.tesctesnk;
	}

	public void setTesctesnk(int tesctesnk) {
		this.tesctesnk = tesctesnk;
	}

	public String getEstdireaf() {
		return this.estdireaf;
	}

	public void setEstdireaf(String estdireaf) {
		this.estdireaf = estdireaf;
	}

	public String getEstteleaf() {
		return this.estteleaf;
	}

	public void setEstteleaf(String estteleaf) {
		this.estteleaf = estteleaf;
	}

	public Date getEsthoratf() {
		return this.esthoratf;
	}

	public void setEsthoratf(Date esthoratf) {
		this.esthoratf = esthoratf;
	}

	public Date getEsthorctf() {
		return this.esthorctf;
	}

	public void setEsthorctf(Date esthorctf) {
		this.esthorctf = esthorctf;
	}

	public String getPaicpaiak() {
		return this.paicpaiak;
	}

	public void setPaicpaiak(String paicpaiak) {
		this.paicpaiak = paicpaiak;
	}

	public String getCiucciuak() {
		return this.ciucciuak;
	}

	public void setCiucciuak(String ciucciuak) {
		this.ciucciuak = ciucciuak;
	}

	public double getEstlatinf() {
		return this.estlatinf;
	}

	public void setEstlatinf(double estlatinf) {
		this.estlatinf = estlatinf;
	}

	public double getEstlongnf() {
		return this.estlongnf;
	}

	public void setEstlongnf(double estlongnf) {
		this.estlongnf = estlongnf;
	}

	public String getEstdestaf() {
		return this.estdestaf;
	}

	public void setEstdestaf(String estdestaf) {
		this.estdestaf = estdestaf;
	}

	public double getEstpestnf() {
		return this.estpestnf;
	}

	public void setEstpestnf(double estpestnf) {
		this.estpestnf = estpestnf;
	}

	public String getEstiactsf() {
		return this.estiactsf;
	}

	public void setEstiactsf(String estiactsf) {
		this.estiactsf = estiactsf;
	}

	@Override
	public Integer getPrimaryKey() {
		return estcestnk;
	}

	@Override
	public void cleanObject() {
		try {
			// DAO para leer ESDIMAES
			IEsdimaesDAO esdimaesDao = SpringAppContext.getAppContext()
					.getBean(IEsdimaesDAO.class);
			// Carga imagenes
			images = esdimaesDao.findByEstablishment(this.estcestnk);
		} catch (Exception e) {/* Error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
	}

	/**
	 * @return the images
	 */
	public List<Esdimaes> getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(List<Esdimaes> images) {
		this.images = images;
	}

}
