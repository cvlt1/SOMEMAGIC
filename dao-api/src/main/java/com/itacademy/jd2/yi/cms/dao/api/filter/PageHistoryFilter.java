package com.itacademy.jd2.yi.cms.dao.api.filter;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;

public class PageHistoryFilter extends AbstractFilter {
	
	private String comment;
	
	private IUserAccount changedBy;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public IUserAccount getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(IUserAccount changedBy) {
		this.changedBy = changedBy;
	}
	
	
	

}
