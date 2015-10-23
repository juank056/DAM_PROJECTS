package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * ChdmensaId generated by hbm2java
 */
public class ChdmensaId implements java.io.Serializable {

	private String usucusuak;
	private String usucusdak;
	private Date mchfregfk;
	private int mchcmchnk;

	public ChdmensaId() {
	}

	public ChdmensaId(String usucusuak, String usucusdak, Date mchfregfk,
			int mchcmchnk) {
		this.usucusuak = usucusuak;
		this.usucusdak = usucusdak;
		this.mchfregfk = mchfregfk;
		this.mchcmchnk = mchcmchnk;
	}

	public String getUsucusuak() {
		return this.usucusuak;
	}

	public void setUsucusuak(String usucusuak) {
		this.usucusuak = usucusuak;
	}

	public String getUsucusdak() {
		return this.usucusdak;
	}

	public void setUsucusdak(String usucusdak) {
		this.usucusdak = usucusdak;
	}

	public Date getMchfregfk() {
		return this.mchfregfk;
	}

	public void setMchfregfk(Date mchfregfk) {
		this.mchfregfk = mchfregfk;
	}

	public int getMchcmchnk() {
		return this.mchcmchnk;
	}

	public void setMchcmchnk(int mchcmchnk) {
		this.mchcmchnk = mchcmchnk;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ChdmensaId))
			return false;
		ChdmensaId castOther = (ChdmensaId) other;

		return ((this.getUsucusuak() == castOther.getUsucusuak()) || (this
				.getUsucusuak() != null && castOther.getUsucusuak() != null && this
				.getUsucusuak().equals(castOther.getUsucusuak())))
				&& ((this.getUsucusdak() == castOther.getUsucusdak()) || (this
						.getUsucusdak() != null
						&& castOther.getUsucusdak() != null && this
						.getUsucusdak().equals(castOther.getUsucusdak())))
				&& ((this.getMchfregfk() == castOther.getMchfregfk()) || (this
						.getMchfregfk() != null
						&& castOther.getMchfregfk() != null && this
						.getMchfregfk().equals(castOther.getMchfregfk())))
				&& (this.getMchcmchnk() == castOther.getMchcmchnk());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsucusuak() == null ? 0 : this.getUsucusuak().hashCode());
		result = 37 * result
				+ (getUsucusdak() == null ? 0 : this.getUsucusdak().hashCode());
		result = 37 * result
				+ (getMchfregfk() == null ? 0 : this.getMchfregfk().hashCode());
		result = 37 * result + this.getMchcmchnk();
		return result;
	}

}
