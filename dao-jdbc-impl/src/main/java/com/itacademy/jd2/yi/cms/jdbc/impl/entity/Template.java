package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;

public class Template extends BaseEntity implements ITemplate {

	private String jspPath;
	private Date created;
	private Date updated;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String getJspPath() {
		return jspPath;
	}

	@Override
	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}

	@Override
	public String toString() {
		return "Template [jspPath=" + jspPath + ", created=" + created + ", updated=" + updated + "]";
	}


	
	
	
	
}
