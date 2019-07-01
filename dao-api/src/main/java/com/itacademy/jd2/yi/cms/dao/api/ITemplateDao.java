package com.itacademy.jd2.yi.cms.dao.api;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;

public interface ITemplateDao extends IDao<ITemplate, Integer> {
	
	void save(ITemplate... entities);
	
	List <ITemplate> find (TemplateFilter filter);
	
	long getCount (TemplateFilter filter);

}
