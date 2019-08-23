package com.itacademy.jd2.yi.cms.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
@Entity
public class UserAccount extends BaseEntity implements IUserAccount {
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
    @Enumerated(EnumType.STRING)
	private UserRole role;
	@Column
    @Enumerated(EnumType.STRING)
	private UserStatus status;
	
	
	
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email ) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {

		this.password = password;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public UserStatus getStatus() {
		return status;
	}
	
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "UserAccount [name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", status=" + status + "]";
	}

	
	
	

}
