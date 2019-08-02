package com.itacademy.jd2.yi.cms.dao.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
@Entity
public class PageHistory extends BaseEntity implements IPageHistory {
	
	@Transient
	private IUserAccount changedBy;
	
	@Transient
	private IPage page;
	
	@Column
	private String comment;
	
	public IUserAccount getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(IUserAccount changedBy) {
		this.changedBy = changedBy;
	}
	public IPage getPage() {
		return page;
	}
	public void setPage(IPage page) {
		this.page = page;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "PageHistory [changedBy=" + changedBy + ", page=" + page + ", comment=" + comment + "]";
	}
	
	
}
