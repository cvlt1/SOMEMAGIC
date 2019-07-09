package com.itacademy.jd2.yi.cms.dao.api.entity.table;

public interface ISite extends IBaseEntity {


	String toString();

	void setBasepath(String basepath);

	String getBasepath();

	void setSiteName(String siteName);

	String getSiteName();
	
	void setId(Integer id);

	Integer getSiteId();

	Integer getId();

	

}
