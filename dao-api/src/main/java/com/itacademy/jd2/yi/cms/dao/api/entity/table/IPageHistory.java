package com.itacademy.jd2.yi.cms.dao.api.entity.table;

public interface IPageHistory extends IBaseEntity {

	void setComment(String comment);

	String getComment();

	void setPage(IPage page);

	IPage getPage();

	void setChangedBy(IUserAccount changedBy);

	IUserAccount getChangedBy();

}
