package com.itacademy.jd2.yi.cms.dao.api.entity.table;

public interface IContentItem extends IBaseEntity {

	String toString();

	void setSiteId(ISite site);

	ISite getSiteId();

	void setTitle(String title);

	String getTitle();

	void setHtml(String html);

	String getHtml();

}
