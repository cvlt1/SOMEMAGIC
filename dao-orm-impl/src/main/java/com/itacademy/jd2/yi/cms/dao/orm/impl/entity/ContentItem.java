package com.itacademy.jd2.yi.cms.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
@Entity
public class ContentItem extends BaseEntity implements IContentItem {
	
	@Column
	private String html;
	@Column
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String title;
	@ManyToOne (fetch = FetchType.LAZY, targetEntity = Site.class)
	private ISite site; // many to one
	
	
	@Override
	public String getHtml() {
		return html;
	}
	@Override
	public void setHtml(String html) {
		this.html = html;
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
	public ISite getSiteId() {
		return site;
	}
	@Override
	public void setSiteId(ISite site) {
		this.site = site;
	}
	@Override
	public String toString() {
		return "ContentItem [html=" + html + ", title=" + title + ", siteId=" + site + "]";
	}

	
	
}
