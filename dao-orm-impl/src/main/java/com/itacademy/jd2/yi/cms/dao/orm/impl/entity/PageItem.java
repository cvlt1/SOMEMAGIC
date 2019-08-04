package com.itacademy.jd2.yi.cms.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
@Entity
public class PageItem extends BaseEntity implements IPageItem {
	
	@ManyToOne (fetch = FetchType.LAZY, targetEntity = Page.class)
	private IPage page; // many to one
	
	@ManyToOne (fetch = FetchType.LAZY, targetEntity = ContentItem.class)
	private IContentItem contentItem; // many to one
	
	@Column
	private Integer position;

	@Override
	public IPage getPage() {
		return page;
	}

	@Override
	public void setPage(IPage page) {
		this.page = page;
	}

	@Override
	public IContentItem getContentItem() {
		return contentItem;
	}

	@Override
	public void setContentItem(IContentItem contentItem) {
		this.contentItem = contentItem;
	}

	@Override
	public Integer getPosition() {
		return position;
	}

	@Override
	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "PageItem [page=" + page + ", contentItem=" + contentItem + ", position=" + position + "]";
	}
	
	
	
	

}
