package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageItemFilter;

public interface IPageItemService {
	
	@Transactional
	void refreshItemPositions(Integer pageId) ;
	
	IPageItem get(Integer id);

    List<IPageItem> getAll();
    @Transactional
    void save(IPageItem... entities);
    @Transactional
    void delete(Integer id);
    @Transactional
    void deleteAll();

    IPageItem createEntity();

	long getCount(PageItemFilter filter);

	List<IPageItem> find(PageItemFilter filter);
	
	@Transactional
	void save(IPageItem entity);


	IPageItem getFullInfo(Integer id);

	Integer getNextPosition(Integer pageId);


}
