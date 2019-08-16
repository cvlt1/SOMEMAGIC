package com.itacademy.jd2.yi.cms.web.dto;

import java.util.Date;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;

public class PageDTO {
	
	private Integer id;
	private String path;
	private PageStatus pageStatus;
	private String pageTitle;
	private Date created;
	private Date updated;
	
	private Integer siteId;
	
	private Integer creatorId;
	private String siteName;
	private String siteBasepath;
	private String creatorName;
	private String creatorPassword;
	private String creatorEmail;
	private UserRole creatorRole;
	private UserStatus creatorStatus;
	private Integer templateId;
	private String jspPath;
	
	
	
	
	
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getJspPath() {
		return jspPath;
	}
	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}

	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getCreatorPassword() {
		return creatorPassword;
	}
	public void setCreatorPassword(String creatorPassword) {
		this.creatorPassword = creatorPassword;
	}
	public String getCreatorEmail() {
		return creatorEmail;
	}
	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}

	public UserRole getCreatorRole() {
		return creatorRole;
	}
	public void setCreatorRole(UserRole creatorRole) {
		this.creatorRole = creatorRole;
	}
	public UserStatus getCreatorStatus() {
		return creatorStatus;
	}
	public void setCreatorStatus(UserStatus creatorStatus) {
		this.creatorStatus = creatorStatus;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public PageStatus getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(PageStatus pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	@Override
	public String toString() {
		return "PageDTO [id=" + id + ", path=" + path + ", pageStatus=" + pageStatus + ", pageTitle=" + pageTitle
				+ ", created=" + created + ", updated=" + updated + ", siteId=" + siteId + ", siteName=" + siteName
				+ ", siteBasepath=" + siteBasepath + ", creatorId=" + creatorId + ", creatorName=" + creatorName
				+ ", creatorPassword=" + creatorPassword + ", creatorEmail=" + creatorEmail + ", creatorRole="
				+ creatorRole + ", creatorStatus=" + creatorStatus + ", templateId=" + templateId + ", jspPath="
				+ jspPath + "]";
	}
	
	
}
