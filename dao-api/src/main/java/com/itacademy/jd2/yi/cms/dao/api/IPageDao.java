package com.itacademy.jd2.yi.cms.dao.api;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;

public interface IPageDao extends IDao<IPage, Integer>{
	
	void save(IPage... entities);
	
	List <IPage> find (PageFilter filter);
	
	long getCount (PageFilter filter);


	IPage getFullInfo(Integer id);

	List<IPage> search(String text);

	IPage getByPagePath(String pagePath);


}
