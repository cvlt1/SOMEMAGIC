package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;

public interface IPageService {
	
    IPage get(Integer id);

    List<IPage> getAll();

    void save(IPage... entities);

    void delete(Integer id);

    void deleteAll();

    IPage createEntity();

	long getCount(PageFilter filter);

	List<IPage> find(PageFilter filter);

	void save(IPage entity);
}
