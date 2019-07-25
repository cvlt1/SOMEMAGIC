package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;

public class Page extends BaseEntity implements IPage {
	
<<<<<<< HEAD
	private ISite siteId;
	private Integer parentId;
	private ITemplate templateId;
=======
	private ISite site;
	private ITemplate template;
>>>>>>> deletedPage
	private String path;
	private PageStatus pageStatus;
	private IUserAccount creator;
<<<<<<< HEAD
	private String title;
	
	
	@Override
	public ISite getSiteId() {
		return siteId;
	}
	@Override
	public void setSiteId(ISite siteId) {
		this.siteId = siteId;
	}


	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
=======
	private String pageTitle;
	
	
	
	
	
	

	@Override
	public ISite getSite() {
		return site;
	}
	@Override
	public void setSite(ISite site) {
		this.site = site;
>>>>>>> deletedPage
	}
	@Override
	public ITemplate getTemplate() {
		return template;
	}
	@Override
	public void setTemplate(ITemplate template) {
		this.template = template;
		
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
	public PageStatus getPageStatus() {
		return pageStatus;
	}
	@Override
	public void setPageStatus(PageStatus pageStatus) {
		this.pageStatus = pageStatus;
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
	public String getPageTitle() {
		return pageTitle;
	}
	@Override
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	@Override
	public String toString() {
		return "Page [site=" + site + ", template=" + template + ", path=" + path + ", pageStatus=" + pageStatus
				+ ", creator=" + creator + ", pageTitle=" + pageTitle + "]";
	}

	
	
	

}
