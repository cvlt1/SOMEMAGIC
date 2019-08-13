package com.itacademy.jd2.yi.cms.web.dto;

import java.util.Date;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;

public class PageItemDTO {
	
	private Integer id;
	private Integer position;
	private Date created;
	private Date updated;
	
	private Integer pageId;
	private String pagePath;
	private Integer siteId;
	private Integer parentId;
	private Integer templateId;
	private PageStatus pageStatus;
	private Integer creatorId;
	private String pageTitle;
	
	private Integer contentItemId;
	private String html;
	private String contentItemTitle;
	
	
	
	
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
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
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
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
	public Integer getContentItemId() {
		return contentItemId;
	}
	public void setContentItemId(Integer contentItemId) {
		this.contentItemId = contentItemId;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getContentItemTitle() {
		return contentItemTitle;
	}
	public void setContentItemTitle(String contentItemTitle) {
		this.contentItemTitle = contentItemTitle;
	}
	@Override
	public String toString() {
		return "PageItemDTO [id=" + id + ", position=" + position + ", created=" + created + ", updated=" + updated
				+ ", pagePath=" + pagePath + ", siteId=" + siteId + ", parentId=" + parentId + ", templateId="
				+ templateId + ", pageStatus=" + pageStatus + ", creatorId=" + creatorId + ", pageTitle=" + pageTitle
				+ ", contentItemId=" + contentItemId + ", html=" + html + ", contentItemTitle=" + contentItemTitle
				+ "]";
	}
	
	
	
	

}
