package com.itacademy.jd2.yi.cms.dao.api;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.ContentItemFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;

public interface IContentItemDao extends IDao<IContentItem, Integer> {
	
	void save(IContentItem... entities);
	
	List <IContentItem> find (ContentItemFilter filter);
	
	long getCount (ContentItemFilter filter);

	IContentItem getFullInfo(Integer id);
	
	List<IContentItem> search(String text);

}
