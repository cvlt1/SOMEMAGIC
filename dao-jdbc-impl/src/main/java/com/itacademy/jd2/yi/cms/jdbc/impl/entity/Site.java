package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;

public class Site extends BaseEntity implements ISite {

	private String siteName;
	private String basepath;
	private Integer siteId;
	
	@Override
	public Integer getSiteId() {
		return siteId;
	}
	
	@Override
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
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
