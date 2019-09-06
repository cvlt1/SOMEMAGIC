package com.itacademy.jd2.yi.cms.dao.api.entity.table;

public interface IPageItem extends IBaseEntity {

	void setPosition(Integer position);

	Integer getPosition();

	void setContentItem(IContentItem contentItem);

	IContentItem getContentItem();

	IPage getPage();

	void setPage(IPage page);

}
