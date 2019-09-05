package com.itacademy.jd2.yi.cms.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
@Entity
public class Site extends BaseEntity implements ISite{
	@Column
	private String siteName;
	@Column
	private String basePath;
	
	
	


	
	
	@Override
	public String getSiteName() {
		return siteName;
	}
	@Override
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	@Override
	public String toString() {
		return "Site [siteName=" + siteName + ", basepath=" + basePath + "]";
	}

}
