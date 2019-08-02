package com.itacademy.jd2.yi.cms.dao.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
@Entity
public class Site extends BaseEntity implements ISite{
	@Column
	private String siteName;
	@Column
	private String basepath;
	
	@Override
	public String getSiteName() {
		return siteName;
	}
	@Override
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	@Override
	public String getBasepath() {
		return basepath;
	}
	@Override
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	
	
	@Override
	public String toString() {
		return "Site [siteName=" + siteName + ", basepath=" + basepath + "]";
	}

}
