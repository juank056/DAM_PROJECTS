package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

/**
 * UsdusublId generated by hbm2java
 */
public class UsdusublId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String usucusuak;
	private String usucusbak;

	public UsdusublId() {
	}

	public UsdusublId(String usucusuak, String usucusbak) {
		this.usucusuak = usucusuak;
		this.usucusbak = usucusbak;
	}

	public String getUsucusuak() {
		return this.usucusuak;
	}

	public void setUsucusuak(String usucusuak) {
		this.usucusuak = usucusuak;
	}

	public String getUsucusbak() {
		return this.usucusbak;
	}

	public void setUsucusbak(String usucusbak) {
		this.usucusbak = usucusbak;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsdusublId))
			return false;
		UsdusublId castOther = (UsdusublId) other;

		return ((this.getUsucusuak() == castOther.getUsucusuak()) || (this
				.getUsucusuak() != null && castOther.getUsucusuak() != null && this
				.getUsucusuak().equals(castOther.getUsucusuak())))
				&& ((this.getUsucusbak() == castOther.getUsucusbak()) || (this
						.getUsucusbak() != null
						&& castOther.getUsucusbak() != null && this
						.getUsucusbak().equals(castOther.getUsucusbak())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsucusuak() == null ? 0 : this.getUsucusuak().hashCode());
		result = 37 * result
				+ (getUsucusbak() == null ? 0 : this.getUsucusbak().hashCode());
		return result;
	}

}
