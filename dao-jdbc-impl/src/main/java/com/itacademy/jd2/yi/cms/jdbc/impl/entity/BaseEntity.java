package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IBaseEntity;

public abstract class BaseEntity implements IBaseEntity {
	
	private Integer id;
	private Date created;
	private Date updated;

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
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
