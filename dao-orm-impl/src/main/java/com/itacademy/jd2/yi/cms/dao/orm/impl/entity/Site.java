package com.itacademy.jd2.yi.cms.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
@Entity
public class Site extends BaseEntity implements ISite{
	@Column
	private String name;
	@Column
	private String basePath;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	@Override
	public String toString() {
		return "Site [siteName=" + name + ", basepath=" + basePath + "]";
	}

}
