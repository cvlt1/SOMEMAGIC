package com.itacademy.jd2.yi.cms.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
@Entity
public class Site extends BaseEntity implements ISite{
	@Column
	private String name;
	@Column
	private String basepath;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getBasepath() {
		return basepath;
	}
	@Override
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	
	
	@Override
	public String toString() {
		return "Site [siteName=" + name + ", basepath=" + basepath + "]";
	}

}
