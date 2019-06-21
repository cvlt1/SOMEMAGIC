package com.itacademy.jd2.yi.cms.dao.api.entity.table;

import java.util.Date;



public interface IUserAccount extends IBaseEntity {

	void setUpdated(Date updated);

	Date getUpdated();

	void setCreated(Date created);

	Date getCreated();

	void setName(String name);

	String getName();


//TODO pull up from UserAccount
}
