package com.itacademy.jd2.yi.cms.dao.api.entity.table;

import java.util.Date;

public interface IBaseEntity {

	void setId(Integer id);

	Integer getId();

	void setUpdated(Date updated);

	Date getUpdated();

	void setCreated(Date created);

	Date getCreated();
	
	
	//TODO pull up from BaseEntity DONE


}