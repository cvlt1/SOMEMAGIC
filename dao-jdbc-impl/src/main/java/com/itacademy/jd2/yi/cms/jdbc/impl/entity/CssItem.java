package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;

public class CssItem extends BaseEntity implements ICssItem {
	
	private String content;
	private ISite site;
	
	
	@Override
	public String getContent() {
		return content;
	}
	@Override
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public ISite getSite() {
		return site;
	}
	@Override
	public void setSite(ISite site) {
		this.site = site;
	}
	@Override
	public String toString() {
		return "CssItem [content=" + content + ", site=" + site + "]";
	}
	@Override
	public void setId(ISite site) {
		this.site = site;
	}

	@Override
	public Integer getSiteId() {
		return getSiteId();
	}
	@Override
	public void setSiteId(ISite siteId) {
		this.site = siteId;
	}
	
	

}
