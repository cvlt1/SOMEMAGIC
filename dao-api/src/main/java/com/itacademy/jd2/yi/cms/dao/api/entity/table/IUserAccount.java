package com.itacademy.jd2.yi.cms.dao.api.entity.table;

import java.util.Date;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserStatus;



public interface IUserAccount extends IBaseEntity {

	void setUpdated(Date updated);

	Date getUpdated();

	void setCreated(Date created);

	Date getCreated();

	void setName(String name);
	
	String getName();

	void setStatus(UserStatus userStatus);

	UserStatus getStatus();

	void setRole(UserRole userRole);

	UserRole getRole();

	void setPassword(String password);

	String getPassword();

	void setEmail(String email);

	String getEmail();
}