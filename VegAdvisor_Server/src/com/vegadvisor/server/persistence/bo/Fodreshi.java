package com.vegadvisor.server.persistence.bo;

// Generated 23-oct-2015 19:39:18 by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Fodreshi generated by hbm2java
 */
public class Fodreshi implements java.io.Serializable,AbstractBO<FodreshiId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FodreshiId id;
	private String usucusuak;
	private Date rhffregff;
	private Date rhfhoratf;
	private String rhfdetaaf;

	public Fodreshi() {
	}

	public Fodreshi(FodreshiId id, String usucusuak, Date rhffregff,
			Date rhfhoratf, String rhfdetaaf) {
		this.id = id;
		this.usucusuak = usucusuak;
		this.rhffregff = rhffregff;
		this.rhfhoratf = rhfhoratf;
		this.rhfdetaaf = rhfdetaaf;
	}

	public FodreshiId getId() {
		return this.id;
	}

	public void setId(FodreshiId id) {
		this.id = id;
	}

	public String getUsucusuak() {
		return this.usucusuak;
	}

	public void setUsucusuak(String usucusuak) {
		this.usucusuak = usucusuak;
	}

	public Date getRhffregff() {
		return this.rhffregff;
	}

	public void setRhffregff(Date rhffregff) {
		this.rhffregff = rhffregff;
	}

	public Date getRhfhoratf() {
		return this.rhfhoratf;
	}

	public void setRhfhoratf(Date rhfhoratf) {
		this.rhfhoratf = rhfhoratf;
	}

	public String getRhfdetaaf() {
		return this.rhfdetaaf;
	}

	public void setRhfdetaaf(String rhfdetaaf) {
		this.rhfdetaaf = rhfdetaaf;
	}

	@Override
	public FodreshiId getPrimaryKey() {
		return id;
	}

	@Override
	public void cleanObject() {
		
	}

}