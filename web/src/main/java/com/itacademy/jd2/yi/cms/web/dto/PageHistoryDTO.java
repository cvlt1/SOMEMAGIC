package com.itacademy.jd2.yi.cms.web.dto;

import java.util.Date;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;

public class PageHistoryDTO {



	private String comment;
	private Integer id;
	private Date created;
	private Date updated;
	private Integer changedBy;
	private Integer pageId;
	
	
	private String userAccountName;
	private String userAccountEmail;
	private String userAccountPassword;
	private UserRole userAccountRole;
	private UserStatus userAccountStatus;
	private String pagePath;
	private Integer siteId;
	private Integer parentId;
	private Integer templateId;
	private PageStatus pageStatus;
	private Integer creatorId;
	private String pageTitle;
	
	
	
	
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
	public String getUserAccountName() {
		return userAccountName;
	}
	public void setUserAccountName(String userAccountName) {
		this.userAccountName = userAccountName;
	}
	public String getUserAccountEmail() {
		return userAccountEmail;
	}
	public void setUserAccountEmail(String userAccountEmail) {
		this.userAccountEmail = userAccountEmail;
	}
	public String getUserAccountPassword() {
		return userAccountPassword;
	}
	public void setUserAccountPassword(String userAccountPassword) {
		this.userAccountPassword = userAccountPassword;
	}
	public UserRole getUserAccountRole() {
		return userAccountRole;
	}
	public void setUserAccountRole(UserRole userAccountRole) {
		this.userAccountRole = userAccountRole;
	}
	public UserStatus getUserAccountStatus() {
		return userAccountStatus;
	}
	public void setUserAccountStatus(UserStatus userAccountStatus) {
		this.userAccountStatus = userAccountStatus;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public PageStatus getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(PageStatus pageStatus) {
		this.pageStatus = pageStatus;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public Integer getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(Integer changedBy) {
		this.changedBy = changedBy;
	}
	
		public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
		public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "PageHistoryDTO [changedBy=" + changedBy + ", pageId=" + pageId + ", comment=" + comment + "]";
	}

	
	
}