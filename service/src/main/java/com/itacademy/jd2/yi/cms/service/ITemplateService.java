package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;

public interface ITemplateService {

    ITemplate get(Integer id);

    List<ITemplate> getAll();

    void save(ITemplate... entities);

    void delete(Integer id);

    void deleteAll();

    ITemplate createEntity();

	long getCount(TemplateFilter filter);

	List<ITemplate> find(TemplateFilter filter);

	void save(ITemplate entity);

    //List<IBrand> find(BrandFilter filter);

    //long getCount(BrandFilter filter);
}