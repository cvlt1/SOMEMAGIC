package com.itacademy.jd2.yi.cms.web.dto;

import java.util.Date;

public class SiteDTO {

	private String siteName;
	private String basepath;
	private Integer id;
	private Date created;
	private Date updated;
	
	
	
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getBasepath() {
		return basepath;
	}
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "SiteDTO [siteName=" + siteName + ", basepath=" + basepath + ", id=" + id + "]";
	}
	
	
}
