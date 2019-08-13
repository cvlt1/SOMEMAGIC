package com.itacademy.jd2.yi.cms.web.dto;

import java.util.Date;

public class CssItemDTO {
	
	private Integer id;
	private String content;
	private Date created;
	private Date updated;
	
	private Integer siteId;
	private String siteName;
	private String siteBasepath;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
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
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteBasepath() {
		return siteBasepath;
	}
	public void setSiteBasepath(String siteBasepath) {
		this.siteBasepath = siteBasepath;
	}
	@Override
	public String toString() {
		return "CssItemDTO [id=" + id + ", content=" + content + ", created=" + created + ", updated=" + updated
				+ ", siteId=" + siteId + ", siteName=" + siteName + ", siteBasepath=" + siteBasepath + "]";
	}
	
	

}
