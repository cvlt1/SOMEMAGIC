package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;



public class UserAccount extends BaseEntity implements IUserAccount {
	
	private String name;
//	private String email;
//	private String password;
//	private String role;
//	private String status;
	private Date created;
	private Date updated;
	
	

//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public Date getCreated() {
		return created;
	}
	@Override
	public void setCreated(Date created) {
		this.created = created;
	}
	@Override
	public Date getUpdated() {
		return updated;
	}
	@Override
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	@Override
	public String toString() {
		return "UserAccount [name=" + name + ", created=" + created + ", updated=" + updated + "]";
	}
	

	
	
	
	
	
	

}
