package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.CssItemFilter;

public interface ICssItemService {

	ICssItem get(Integer id);

	List<ICssItem> getAll();

	void save(ICssItem entity);

	void delete(Integer id);

	void deleteAll();

	ICssItem createEntity();


	//ICssItem getFullInfo(final Integer id);

	//void save(IModel entity, IModelInfo infoEntity);

	long getCount(CssItemFilter filter);

	List<ICssItem> find(CssItemFilter filter);
}
