package com.itacademy.jd2.yi.cms.dao.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
@Entity
public class CssItem extends BaseEntity implements ICssItem {
	@Column
	private String content;
	
	@Transient
	private ISite site;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ISite getSite() {
		return site;
	}
	public void setSite(ISite site) {
		this.site = site;
	}
	@Override
	public String toString() {
		return "CssItem [content=" + content + ", site=" + site + "]";
	}
	
	

}
