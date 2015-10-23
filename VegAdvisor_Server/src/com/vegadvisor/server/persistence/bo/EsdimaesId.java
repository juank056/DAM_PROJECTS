package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

/**
 * EsdimaesId generated by hbm2java
 */
public class EsdimaesId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int estcestnk;
	private int iesciesnk;

	public EsdimaesId() {
	}

	public EsdimaesId(int estcestnk, int iesciesnk) {
		this.estcestnk = estcestnk;
		this.iesciesnk = iesciesnk;
	}

	public int getEstcestnk() {
		return this.estcestnk;
	}

	public void setEstcestnk(int estcestnk) {
		this.estcestnk = estcestnk;
	}

	public int getIesciesnk() {
		return this.iesciesnk;
	}

	public void setIesciesnk(int iesciesnk) {
		this.iesciesnk = iesciesnk;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EsdimaesId))
			return false;
		EsdimaesId castOther = (EsdimaesId) other;

		return (this.getEstcestnk() == castOther.getEstcestnk())
				&& (this.getIesciesnk() == castOther.getIesciesnk());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getEstcestnk();
		result = 37 * result + this.getIesciesnk();
		return result;
	}

}