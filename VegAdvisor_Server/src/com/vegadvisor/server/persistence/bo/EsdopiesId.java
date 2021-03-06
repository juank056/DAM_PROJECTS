package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * EsdopiesId generated by hbm2java
 */
public class EsdopiesId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int estcestnk;
	private Date oesfregfk;
	private int oescoesnk;

	public EsdopiesId() {
	}

	public EsdopiesId(int estcestnk, Date oesfregfk, int oescoesnk) {
		this.estcestnk = estcestnk;
		this.oesfregfk = oesfregfk;
		this.oescoesnk = oescoesnk;
	}

	public int getEstcestnk() {
		return this.estcestnk;
	}

	public void setEstcestnk(int estcestnk) {
		this.estcestnk = estcestnk;
	}

	public Date getOesfregfk() {
		return this.oesfregfk;
	}

	public void setOesfregfk(Date oesfregfk) {
		this.oesfregfk = oesfregfk;
	}

	public int getOescoesnk() {
		return this.oescoesnk;
	}

	public void setOescoesnk(int oescoesnk) {
		this.oescoesnk = oescoesnk;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EsdopiesId))
			return false;
		EsdopiesId castOther = (EsdopiesId) other;

		return (this.getEstcestnk() == castOther.getEstcestnk())
				&& ((this.getOesfregfk() == castOther.getOesfregfk()) || (this
						.getOesfregfk() != null
						&& castOther.getOesfregfk() != null && this
						.getOesfregfk().equals(castOther.getOesfregfk())))
				&& (this.getOescoesnk() == castOther.getOescoesnk());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getEstcestnk();
		result = 37 * result
				+ (getOesfregfk() == null ? 0 : this.getOesfregfk().hashCode());
		result = 37 * result + this.getOescoesnk();
		return result;
	}

}
