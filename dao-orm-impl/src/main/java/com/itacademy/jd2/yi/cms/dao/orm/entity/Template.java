package com.itacademy.jd2.yi.cms.dao.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
@Entity
public class Template extends BaseEntity implements ITemplate {
	@Column
	private String jspPath;
	
	
	
	public String getJspPath() {
		return jspPath;
	}

	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}




	
	

}
