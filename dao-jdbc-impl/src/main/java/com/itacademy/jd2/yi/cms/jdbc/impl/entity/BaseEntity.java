package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IBaseEntity;

public abstract class BaseEntity implements IBaseEntity {
	
	private Integer id;
	private Date created;
	private Date updated;

<<<<<<< HEAD
	@Override
=======
>>>>>>> master
	public Date getCreated() {
		return created;
	}

<<<<<<< HEAD
	@Override
=======
>>>>>>> master
	public void setCreated(Date created) {
		this.created = created;
	}

<<<<<<< HEAD
	@Override
=======
>>>>>>> master
	public Date getUpdated() {
		return updated;
	}

<<<<<<< HEAD
	@Override
=======
>>>>>>> master
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

<<<<<<< HEAD
	@Override
	public Integer getId() {
		return id;
	}

	@Override
=======
	@Override
	public Integer getId() {
		return id;
	}

	@Override
>>>>>>> master
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

<<<<<<< HEAD
}
=======
}
>>>>>>> master
