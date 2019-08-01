package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;

public class PageHistory extends BaseEntity implements IPageHistory {
	
	private IUserAccount changedBy;
	private IPage page;
	private String comment;
	
	
	@Override
	public IUserAccount getChangedBy() {
		return changedBy;
	}
	@Override
	public void setChangedBy(IUserAccount changedBy) {
		this.changedBy = changedBy;
	}
	@Override
	public IPage getPage() {
		return page;
	}
	@Override
	public void setPage(IPage page) {
		this.page = page;
	}
	@Override
	public String getComment() {
		return comment;
	}
	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	

}
