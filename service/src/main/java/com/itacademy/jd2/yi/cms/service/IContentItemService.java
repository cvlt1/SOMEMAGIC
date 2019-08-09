package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.ContentItemFilter;

public interface IContentItemService {
	
    IContentItem get(Integer id);

    List<IContentItem> getAll();
    @Transactional
    void save(IContentItem... entities);
    @Transactional
    void delete(Integer id);
    @Transactional
    void deleteAll();

    IContentItem createEntity();

	long getCount(ContentItemFilter filter);

	List<IContentItem> find(ContentItemFilter filter);
	
	@Transactional
	void save(IContentItem entity);


	IContentItem getFullInfo(Integer id);
}
