package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;

public interface IPageService {
	
    IPage get(Integer id);

    List<IPage> getAll();
    @Transactional
    void save(IPage... entities);
    @Transactional
    void delete(Integer id);
    @Transactional
    void deleteAll();

    IPage createEntity();

	long getCount(PageFilter filter);

	List<IPage> find(PageFilter filter);
	
	@Transactional
	void save(IPage entity);


	IPage getFullInfo(Integer id);
	
	
	@Transactional
	List<IPage> search(String text);
}
