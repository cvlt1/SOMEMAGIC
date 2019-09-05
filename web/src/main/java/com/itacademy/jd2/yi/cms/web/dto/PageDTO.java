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
	private String viewName;
	private Integer pageItemId;
	private Integer contentItemId;
	private Integer pageItemPosition;
	
	
	
	
	
	public Integer getPageItemId() {
		return pageItemId;
	}
	public void setPageItemId(Integer pageItemId) {
		this.pageItemId = pageItemId;
	}
	public Integer getContentItemId() {
		return contentItemId;
	}
	public void setContentItemId(Integer contentItemId) {
		this.contentItemId = contentItemId;
	}
	public Integer getPageItemPosition() {
		return pageItemPosition;
	}
	public void setPageItemPosition(Integer pageItemPosition) {
		this.pageItemPosition = pageItemPosition;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
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
				+ ", created=" + created + ", updated=" + updated + ", siteId=" + siteId + ", creatorId=" + creatorId
				+ ", siteName=" + siteName + ", siteBasepath=" + siteBasepath + ", creatorName=" + creatorName
				+ ", creatorPassword=" + creatorPassword + ", creatorEmail=" + creatorEmail + ", creatorRole="
				+ creatorRole + ", creatorStatus=" + creatorStatus + ", templateId=" + templateId + ", viewName="
				+ viewName + ", pageItemId=" + pageItemId + ", contentItemId=" + contentItemId + ", pageItemPosition="
				+ pageItemPosition + "]";
	}
	
	
}
