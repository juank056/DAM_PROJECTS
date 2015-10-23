package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

/**
 * Evmevent generated by hbm2java
 */
public class Evmevent implements java.io.Serializable,AbstractBO<EvmeventId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EvmeventId id;
	private String usucusuak;
	private int estcestnk;
	private String estloceaf;
	private long evelatinf;
	private long evelongnf;
	private int tevctevnk;
	private String evedeveaf;
	private int evenparnf;

	public Evmevent() {
	}

	public Evmevent(EvmeventId id, String usucusuak, int estcestnk,
			String estloceaf, long evelatinf, long evelongnf, int tevctevnk,
			String evedeveaf, int evenparnf) {
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

	public long getEvelatinf() {
		return this.evelatinf;
	}

	public void setEvelatinf(long evelatinf) {
		this.evelatinf = evelatinf;
	}

	public long getEvelongnf() {
		return this.evelongnf;
	}

	public void setEvelongnf(long evelongnf) {
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

	@Override
	public EvmeventId getPrimaryKey() {
		return id;
	}

	@Override
	public void cleanObject() {
		
	}

}
