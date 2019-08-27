package com.itacademy.jd2.yi.cms.dao.api.entity.table;

import java.util.Date;

public interface ITemplate extends IBaseEntity {

	String toString();

	void setUpdated(Date updated);

	Date getUpdated();

	void setCreated(Date created);

	Date getCreated();

	void setViewName(String viewName);

	String getViewName();

	
	// TODO PULL UP
}
