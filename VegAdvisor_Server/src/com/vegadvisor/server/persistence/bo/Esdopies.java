package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

/**
 * Esdopies generated by hbm2java
 */
public class Esdopies implements java.io.Serializable,AbstractBO<EsdopiesId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EsdopiesId id;
	private String usucusuak;
	private int oesnestnf;
	private String oesdetoaf;

	public Esdopies() {
	}

	public Esdopies(EsdopiesId id, String usucusuak, int oesnestnf,
			String oesdetoaf) {
		this.id = id;
		this.usucusuak = usucusuak;
		this.oesnestnf = oesnestnf;
		this.oesdetoaf = oesdetoaf;
	}

	public EsdopiesId getId() {
		return this.id;
	}

	public void setId(EsdopiesId id) {
		this.id = id;
	}

	public String getUsucusuak() {
		return this.usucusuak;
	}

	public void setUsucusuak(String usucusuak) {
		this.usucusuak = usucusuak;
	}

	public int getOesnestnf() {
		return this.oesnestnf;
	}

	public void setOesnestnf(int oesnestnf) {
		this.oesnestnf = oesnestnf;
	}

	public String getOesdetoaf() {
		return this.oesdetoaf;
	}

	public void setOesdetoaf(String oesdetoaf) {
		this.oesdetoaf = oesdetoaf;
	}

	@Override
	public EsdopiesId getPrimaryKey() {
		return id;
	}

	@Override
	public void cleanObject() {
		
	}

}
