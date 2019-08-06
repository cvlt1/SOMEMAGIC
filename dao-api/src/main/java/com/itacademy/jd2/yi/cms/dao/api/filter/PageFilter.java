package com.itacademy.jd2.yi.cms.dao.api.filter;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;

public class PageFilter extends AbstractFilter {
	
	private boolean FetchSite;
	

	private ISite site; // many to one
	
	private ITemplate template;
	
	private IUserAccount creator;
	
	private String path;
	
	private String pageTitle;
	
	private PageStatus pageStatus;
	
	
	

	

	public ISite getSite() {
		return site;
	}

	public void setSite(ISite site) {
		this.site = site;
	}

	public ITemplate getTemplate() {
		return template;
	}

	public void setTemplate(ITemplate template) {
		this.template = template;
	}

	public IUserAccount getCreator() {
		return creator;
	}

	public void setCreator(IUserAccount creator) {
		this.creator = creator;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public PageStatus getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(PageStatus pageStatus) {
		this.pageStatus = pageStatus;
	}

	public boolean getFetchSite() {
		return FetchSite;
	}

	public void setFetchSite(boolean fetchSite) {
		FetchSite = fetchSite;
	}

}
