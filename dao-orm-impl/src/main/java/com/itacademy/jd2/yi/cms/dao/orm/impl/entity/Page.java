package com.itacademy.jd2.yi.cms.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
@Entity
public class Page extends BaseEntity implements IPage {
	
	
	@ManyToOne (fetch = FetchType.LAZY, targetEntity = Site.class)
	private ISite site; // many to one
	
	@ManyToOne (fetch = FetchType.LAZY, targetEntity = Template.class)
	private ITemplate template;
	
	@ManyToOne  (fetch = FetchType.LAZY, targetEntity = UserAccount.class) //DO NOT USE EAGER
	private IUserAccount creator;
	
	@Column
	private String path;
	
	@Column
	private String pageTitle;
	
	@Column
    @Enumerated(EnumType.STRING)
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

	@Override
	public String toString() {
		return "Page [site=" + site + ", template=" + template + ", creator=" + creator + ", path=" + path
				+ ", pageTitle=" + pageTitle + ", pageStatus=" + pageStatus + "]";
	}
	
	

}
