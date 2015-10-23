package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * EvmeventId generated by hbm2java
 */
public class EvmeventId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String paicpaiak;
	private String ciucciuak;
	private Date evefevefk;
	private int evecevenk;

	public EvmeventId() {
	}

	public EvmeventId(String paicpaiak, String ciucciuak, Date evefevefk,
			int evecevenk) {
		this.paicpaiak = paicpaiak;
		this.ciucciuak = ciucciuak;
		this.evefevefk = evefevefk;
		this.evecevenk = evecevenk;
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

	public Date getEvefevefk() {
		return this.evefevefk;
	}

	public void setEvefevefk(Date evefevefk) {
		this.evefevefk = evefevefk;
	}

	public int getEvecevenk() {
		return this.evecevenk;
	}

	public void setEvecevenk(int evecevenk) {
		this.evecevenk = evecevenk;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EvmeventId))
			return false;
		EvmeventId castOther = (EvmeventId) other;

		return ((this.getPaicpaiak() == castOther.getPaicpaiak()) || (this
				.getPaicpaiak() != null && castOther.getPaicpaiak() != null && this
				.getPaicpaiak().equals(castOther.getPaicpaiak())))
				&& ((this.getCiucciuak() == castOther.getCiucciuak()) || (this
						.getCiucciuak() != null
						&& castOther.getCiucciuak() != null && this
						.getCiucciuak().equals(castOther.getCiucciuak())))
				&& ((this.getEvefevefk() == castOther.getEvefevefk()) || (this
						.getEvefevefk() != null
						&& castOther.getEvefevefk() != null && this
						.getEvefevefk().equals(castOther.getEvefevefk())))
				&& (this.getEvecevenk() == castOther.getEvecevenk());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPaicpaiak() == null ? 0 : this.getPaicpaiak().hashCode());
		result = 37 * result
				+ (getCiucciuak() == null ? 0 : this.getCiucciuak().hashCode());
		result = 37 * result
				+ (getEvefevefk() == null ? 0 : this.getEvefevefk().hashCode());
		result = 37 * result + this.getEvecevenk();
		return result;
	}

}