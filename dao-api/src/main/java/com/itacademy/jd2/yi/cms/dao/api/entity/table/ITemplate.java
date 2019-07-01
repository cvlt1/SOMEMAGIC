package com.itacademy.jd2.yi.cms.dao.api.entity.table;

import java.util.Date;

public interface ITemplate extends IBaseEntity {

	String toString();

	void setJspPath(String jspPath);

	String getJspPath();
	
	void setUpdated(Date updated);

	Date getUpdated();

	void setCreated(Date created);

	Date getCreated();

	
	// TODO PULL UP
}
