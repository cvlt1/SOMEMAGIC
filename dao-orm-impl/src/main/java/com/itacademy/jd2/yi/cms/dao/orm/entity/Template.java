package com.itacademy.jd2.yi.cms.dao.orm.entity;

import javax.persistence.Column;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;

public class Template extends BaseEntity implements ITemplate {
	@Column
	private String jspPath;
	
	private String upperCaseName;
	
    public Template(String jspPath, Integer id) {
        super();
        setId(id);
        this.jspPath = jspPath;
    }
    
    
    public Template() {
    }
	
	
	public String getJspPath() {
		return jspPath;
	}

	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
		setUpperCaseName(jspPath.toUpperCase());
	}


	public String getUpperCaseName() {
		return upperCaseName;
	}


	public void setUpperCaseName(final String upperCaseName) {
		this.upperCaseName = upperCaseName;
	}
	
	

}
