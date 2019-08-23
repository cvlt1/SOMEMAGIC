package com.itacademy.jd2.yi.cms.dao.api;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.IDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;

public interface ISiteDao extends IDao<ISite, Integer> {
	
	void save(ISite... entities);
	
	List <ISite> find (SiteFilter filter);
	
	long getCount (SiteFilter filter);

	ISite getByBasePath(String basePath);

}
