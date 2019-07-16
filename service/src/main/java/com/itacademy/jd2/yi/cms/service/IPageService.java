package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.filter.CssItemFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;

public interface IPageService {

	IPage get(Integer id);

	List<IPage> getAll();

	void save(IPage entity);

	void delete(Integer id);

	void deleteAll();

	IPage createEntity();


	//ICssItem getFullInfo(final Integer id);

	void save(IPage... entities);

	long getCount(PageFilter filter);

	List<IPage> find(PageFilter filter);
}
