package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;

public interface ISiteService {

    ISite get(Integer id);

    List<ISite> getAll();

    void save(ISite... entities);

    void delete(Integer id);

    void deleteAll();

    ISite createEntity();

	long getCount(SiteFilter filter);

	List<ISite> find(SiteFilter filter);

	void save(ISite entity);

    //List<IBrand> find(BrandFilter filter);

    //long getCount(BrandFilter filter);
}