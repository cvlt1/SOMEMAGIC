package com.itacademy.jd2.yi.cms.dao.api.entity.table;

public interface IContentItem extends IBaseEntity {

	String toString();

	void setSite(ISite site);

	ISite getSite();

	void setTitle(String title);

	String getTitle();

	void setHtml(String html);

	String getHtml();


}
