package com.itacademy.jd2.yi.cms.dao.api.entity.table;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;

public interface IPage extends IBaseEntity {

	String toString();

	void setPageTitle(String pageTitle);

	String getPageTitle();

	void setCreator(IUserAccount creator);

	IUserAccount getCreator();

	void setPageStatus(PageStatus pageStatus);

	PageStatus getPageStatus();

	void setPath(String path);

	String getPath();

	void setTemplate(ITemplate template);

	ITemplate getTemplate();

	void setSite(ISite site);

	ISite getSite();

	void setPageItem(IPageItem pageItem);

	IPageItem getPageItem();



}
