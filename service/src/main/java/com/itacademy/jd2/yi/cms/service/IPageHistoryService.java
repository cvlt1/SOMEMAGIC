package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageHistoryFilter;

public interface IPageHistoryService {
	
	IPageHistory get(Integer id);

    List<IPageHistory> getAll();
    @Transactional
    void save(IPageHistory... entities);
    @Transactional
    void delete(Integer id);
    @Transactional
    void deleteAll();

    IPageHistory createEntity();

	long getCount(PageHistoryFilter filter);

	List<IPageHistory> find(PageHistoryFilter filter);
	
	@Transactional
	void save(IPageHistory entity);


	IPageHistory getFullInfo(Integer id);
}
