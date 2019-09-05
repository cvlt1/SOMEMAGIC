package com.itacademy.jd2.yi.cms.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
@Indexed
@Entity
@OnDelete( action = OnDeleteAction.CASCADE)

public class Page extends BaseEntity implements IPage {
	
	
	@ManyToOne (fetch = FetchType.LAZY, targetEntity = Site.class)
	private ISite site; // many to one
	
	@ManyToOne (fetch = FetchType.LAZY, targetEntity = PageItem.class)
	private IPageItem pageItem;

	@ManyToOne (fetch = FetchType.LAZY, targetEntity = Template.class)
	private ITemplate template;
	
	@ManyToOne  (fetch = FetchType.LAZY, targetEntity = UserAccount.class) //DO NOT USE EAGER
	private IUserAccount creator;
	
	@Column
	private String path;
	
	@Column
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
	public IPageItem getPageItem() {
		return pageItem;
	}

	@Override
	public void setPageItem(IPageItem pageItem) {
		this.pageItem = pageItem;
	}

	@Override
	public String toString() {
		return "Page [site=" + site + ", pageItem=" + pageItem + ", template=" + template + ", creator=" + creator
				+ ", path=" + path + ", pageTitle=" + pageTitle + ", pageStatus=" + pageStatus + "]";
	}


	
	

}
