package com.vegadvisor.server.persistence.bo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.vegadvisor.server.persistence.dao.ICsptieveDAO;
import com.vegadvisor.server.persistence.dao.IEsmestabDAO;
import com.vegadvisor.server.persistence.dao.IEvdimaevDAO;
import com.vegadvisor.server.utils.Constants;
import com.vegadvisor.server.utils.LogLogger;
import com.vegadvisor.server.utils.SpringAppContext;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

/**
 * Evmevent generated by hbm2java
 */
public class Evmevent implements java.io.Serializable, AbstractBO<EvmeventId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EvmeventId id;
	private Date evehoratf;
	private String usucusuak;
	private int estcestnk;
	private String estloceaf;
	private double evelatinf;
	private double evelongnf;
	private int tevctevnk;
	private String evedeveaf;
	private int evenparnf;
	private String eveiactsf;
	// Indicador de participación de usuario
	private String userParticipating;
	// Imagenes del evento
	private List<Evdimaev> images;
	// Nombre del establecimiento
	private String establishmentName;
	// Nombre de tipo de evento
	private String eventTypeName;

	public Evmevent() {
	}

	public Evmevent(EvmeventId id, String usucusuak, int estcestnk,
			String estloceaf, double evelatinf, double evelongnf,
			int tevctevnk, String evedeveaf, int evenparnf) {
		this.id = id;
		this.usucusuak = usucusuak;
		this.estcestnk = estcestnk;
		this.estloceaf = estloceaf;
		this.evelatinf = evelatinf;
		this.evelongnf = evelongnf;
		this.tevctevnk = tevctevnk;
		this.evedeveaf = evedeveaf;
		this.evenparnf = evenparnf;
	}

	public EvmeventId getId() {
		return this.id;
	}

	public void setId(EvmeventId id) {
		this.id = id;
	}

	/**
	 * @return the evehoratf
	 */
	public Date getEvehoratf() {
		return evehoratf;
	}

	/**
	 * @param evehoratf
	 *            the evehoratf to set
	 */
	public void setEvehoratf(Date evehoratf) {
		this.evehoratf = evehoratf;
	}

	public String getUsucusuak() {
		return this.usucusuak;
	}

	public void setUsucusuak(String usucusuak) {
		this.usucusuak = usucusuak;
	}

	public int getEstcestnk() {
		return this.estcestnk;
	}

	public void setEstcestnk(int estcestnk) {
		this.estcestnk = estcestnk;
	}

	public String getEstloceaf() {
		return this.estloceaf;
	}

	public void setEstloceaf(String estloceaf) {
		this.estloceaf = estloceaf;
	}

	public double getEvelatinf() {
		return this.evelatinf;
	}

	public void setEvelatinf(double evelatinf) {
		this.evelatinf = evelatinf;
	}

	public double getEvelongnf() {
		return this.evelongnf;
	}

	public void setEvelongnf(double evelongnf) {
		this.evelongnf = evelongnf;
	}

	public int getTevctevnk() {
		return this.tevctevnk;
	}

	public void setTevctevnk(int tevctevnk) {
		this.tevctevnk = tevctevnk;
	}

	public String getEvedeveaf() {
		return this.evedeveaf;
	}

	public void setEvedeveaf(String evedeveaf) {
		this.evedeveaf = evedeveaf;
	}

	public int getEvenparnf() {
		return this.evenparnf;
	}

	public void setEvenparnf(int evenparnf) {
		this.evenparnf = evenparnf;
	}

	/**
	 * @return the userParticipating
	 */
	public String getUserParticipating() {
		return userParticipating;
	}

	/**
	 * @param userParticipating
	 *            the userParticipating to set
	 */
	public void setUserParticipating(String userParticipating) {
		this.userParticipating = userParticipating;
	}

	/**
	 * @return the images
	 */
	public List<Evdimaev> getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(List<Evdimaev> images) {
		this.images = images;
	}

	/**
	 * @return the eveiactsf
	 */
	public String getEveiactsf() {
		return eveiactsf;
	}

	/**
	 * @param eveiactsf
	 *            the eveiactsf to set
	 */
	public void setEveiactsf(String eveiactsf) {
		this.eveiactsf = eveiactsf;
	}

	/**
	 * @return the establishmentName
	 */
	public String getEstablishmentName() {
		return establishmentName;
	}

	/**
	 * @param establishmentName
	 *            the establishmentName to set
	 */
	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}

	/**
	 * @return the eventTypeName
	 */
	public String getEventTypeName() {
		return eventTypeName;
	}

	/**
	 * @param eventTypeName
	 *            the eventTypeName to set
	 */
	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	@Override
	public EvmeventId getPrimaryKey() {
		return id;
	}

	@Override
	public void cleanObject() {
		try {
			// Campos por defecto
			establishmentName = Constants.BLANKS;
			eventTypeName = Constants.BLANKS;
			// DAO para leer EVDIMAEV
			IEvdimaevDAO evdimaevDao = SpringAppContext.getAppContext()
					.getBean(IEvdimaevDAO.class);
			// Carga imagenes
			images = evdimaevDao.findByEvent(id.getPaicpaiak(),
					id.getCiucciuak(), id.getEvefevefk(), id.getEvecevenk());
			// DAO para leer ESMESTAB
			IEsmestabDAO esmestabDao = SpringAppContext.getAppContext()
					.getBean(IEsmestabDAO.class);
			if (estcestnk != 0) {
				// Obtiene establecimiento
				Esmestab estab = esmestabDao.findById(estcestnk);
				if (estab != null) {
					estab.cleanObject();
					establishmentName = estab.getEstnestaf();
				}
			}
			// DAO para leer CSPTIEVE
			ICsptieveDAO csptieveDao = SpringAppContext.getAppContext()
					.getBean(ICsptieveDAO.class);
			if (tevctevnk != 0) {
				// Obtiene tipo evento
				Csptieve tieve = csptieveDao.findById(tevctevnk);
				if (tieve != null) {
					tieve.cleanObject();
					eventTypeName = tieve.getTevdtesaf();
				}
			}
		} catch (Exception e) {/* Error */
			LogLogger.getInstance(this.getClass()).logger(
					ExceptionUtils.getFullStackTrace(e), LogLogger.ERROR);
		}
	}

}
