package com.itacademy.jd2.yi.cms.dao.api.entity.table;

public interface IPageItem extends IBaseEntity {

	String toString();

	void setPosition(Integer position);

	Integer getPosition();

	void setContentItem(IContentItem contentItem);

	IContentItem getContentItem();

	void setPage(IPage page);

	IPage getPage();

}
