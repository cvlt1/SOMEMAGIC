package com.itacademy.jd2.yi.cms.web.dto;

import java.util.Date;

public class TemplateDTO {

	private String jspPath;
	private Date created;
	private Date updated;
	private Integer id;

	public Integer getId() {
		return id;
	}


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

	public String getJspPath() {
		return jspPath;
	}

	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}

	@Override
	public String toString() {
		return "Template [jspPath=" + jspPath + ", created=" + created + ", updated=" + updated + "]";
	}

	public void setId(Integer id) {
		this.id = id;		
	}
}
