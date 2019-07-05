package com.itacademy.jd2.yi.cms.dao.api.entity.table;

import java.util.Date;

public interface ISite extends IBaseEntity {

	String toString();

	void setBasepath(String basepath);

	String getBasepath();

	void setSiteName(String siteName);

	String getSiteName();
	
	void setUpdated(Date updated);

	Date getUpdated();

	void setCreated(Date created);

	Date getCreated();

}
