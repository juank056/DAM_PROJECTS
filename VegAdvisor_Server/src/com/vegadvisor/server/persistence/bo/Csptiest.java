package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

/**
 * Csptiest generated by hbm2java
 */
public class Csptiest implements java.io.Serializable,AbstractBO<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tesctesnk;
	private String tesntesaf;
	private String tesdtesaf;

	public Csptiest() {
	}

	public Csptiest(String tesntesaf, String tesdtesaf) {
		this.tesntesaf = tesntesaf;
		this.tesdtesaf = tesdtesaf;
	}

	public Integer getTesctesnk() {
		return this.tesctesnk;
	}

	public void setTesctesnk(Integer tesctesnk) {
		this.tesctesnk = tesctesnk;
	}

	public String getTesntesaf() {
		return this.tesntesaf;
	}

	public void setTesntesaf(String tesntesaf) {
		this.tesntesaf = tesntesaf;
	}

	public String getTesdtesaf() {
		return this.tesdtesaf;
	}

	public void setTesdtesaf(String tesdtesaf) {
		this.tesdtesaf = tesdtesaf;
	}

	@Override
	public Integer getPrimaryKey() {
		return tesctesnk;
	}

	@Override
	public void cleanObject() {
		
	}

}
