package com.itacademy.jd2.yi.cms.dao.api.entity.table;


public interface ICssItem extends IBaseEntity {

	String toString();

	void setSite(ISite site);

	ISite getSite();

	void setContent(String content);

	String getContent();


}
