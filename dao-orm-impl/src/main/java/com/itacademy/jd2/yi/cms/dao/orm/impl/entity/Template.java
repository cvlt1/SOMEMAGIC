package com.itacademy.jd2.yi.cms.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
@Entity
public class Template extends BaseEntity implements ITemplate {
	@Column
	private String viewName;

	@Override
	public String getViewName() {
		return viewName;
	}

	@Override
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	
	
	
	





	
	

}
