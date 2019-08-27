package com.itacademy.jd2.yi.cms.web.dto;

import java.util.Date;

import javax.validation.constraints.Size;

public class TemplateDTO {
	 @Size(min = 3, max = 50)
	private String viewName;
	private Date created;
	private Date updated;
	private Integer id;

	public Integer getId() {
		return id;
	}
	
	


	public String getViewName() {
		return viewName;
	}




	public void setViewName(String viewName) {
		this.viewName = viewName;
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
	

	@Override
	public String toString() {
		return "Template [jspPath=" + viewName + ", created=" + created + ", updated=" + updated + "]";
	}

	public void setId(Integer id) {
		this.id = id;		
	}
}
