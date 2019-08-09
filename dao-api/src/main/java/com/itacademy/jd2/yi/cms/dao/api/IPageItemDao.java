package com.itacademy.jd2.yi.cms.dao.api;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageItemFilter;

public interface IPageItemDao extends IDao<IPageItem, Integer>{
	
	void save(IPageItem... entities);
	
	List <IPageItem> find (PageItemFilter filter);
	
	long getCount (PageItemFilter filter);

	IPageItem getFullInfo(Integer id);

}

