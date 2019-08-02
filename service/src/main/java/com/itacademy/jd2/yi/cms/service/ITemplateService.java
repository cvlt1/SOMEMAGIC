package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;

public interface ITemplateService {

    ITemplate get(Integer id);

    List<ITemplate> getAll();
    @Transactional
    void save(ITemplate... entities);
    @Transactional
    void delete(Integer id);
    @Transactional
    void deleteAll();
    @Transactional
    ITemplate createEntity();

	long getCount(TemplateFilter filter);

	List<ITemplate> find(TemplateFilter filter);
	@Transactional
	void save(ITemplate entity);

    //List<IBrand> find(BrandFilter filter);

    //long getCount(BrandFilter filter);
}