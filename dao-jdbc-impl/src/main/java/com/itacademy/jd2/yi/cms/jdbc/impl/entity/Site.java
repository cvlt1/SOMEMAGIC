package com.itacademy.jd2.yi.cms.jdbc.impl.entity;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;

public class Site extends BaseEntity implements ISite {

	private String name;
	private String basepath;
	
	@Override
	public String getName() {
		return name;
	}
	@Override
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
