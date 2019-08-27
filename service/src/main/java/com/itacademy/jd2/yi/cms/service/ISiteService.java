package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;

public interface ISiteService {

    ISite get(Integer id);

    List<ISite> getAll();
    @Transactional
    void save(ISite... entities);
    @Transactional
    void delete(Integer id);
    @Transactional
    void deleteAll();
    @Transactional
    ISite createEntity();

	long getCount(SiteFilter filter);

	List<ISite> find(SiteFilter filter);
	@Transactional
	void save(ISite entity);

	ISite get(String basePath);


}