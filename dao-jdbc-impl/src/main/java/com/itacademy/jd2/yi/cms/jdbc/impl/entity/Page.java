package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;

public class Page extends BaseEntity implements IPage {
	
	private ISite siteId;
	private IPage parentId;
	private ITemplate templateId;
	private String path;
	private PageStatus status;
	private IUserAccount creator;
	private String title;
	@Override
	public ISite getSiteId() {
		return siteId;
	}
	@Override
	public void setSiteId(ISite siteId) {
		this.siteId = siteId;
	}

	@Override
	public IPage getParentId() {
		return parentId;
	}
	@Override
	public void setParentId(IPage parentId) {
		this.parentId = parentId;
	}
	@Override
	public ITemplate getTemplateId() {
		return templateId;
	}
	@Override
	public void setTemplateId(ITemplate templateId) {
		this.templateId = templateId;
	}
	@Override
	public String getPath() {
		return path;
	}
	@Override
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public PageStatus getStatus() {
		return status;
	}
	@Override
	public void setStatus(PageStatus status) {
		this.status = status;
	}
	@Override
	public IUserAccount getCreator() {
		return creator;
	}
	@Override
	public void setCreator(IUserAccount creator) {
		this.creator = creator;
	}
	@Override
	public String getTitle() {
		return title;
	}
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Page [siteId=" + siteId + ", parentId=" + parentId + ", templateId=" + templateId + ", path=" + path
				+ ", status=" + status + ", creator=" + creator + ", title=" + title + "]";
	}
	
	
}
