package com.itacademy.jd2.yi.cms.dao.api;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageHistoryFilter;

public interface IPageHistoryDao extends IDao<IPageHistory, Integer>{
	
	void save(IPageHistory... entities);
	
	List <IPageHistory> find (PageHistoryFilter filter);
	
	long getCount (PageHistoryFilter filter);

}