package com.itacademy.jd2.yi.cms.service.impl;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.yi.cms.dao.api.IPageItemDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageItemFilter;
import com.itacademy.jd2.yi.cms.service.IPageItemService;

@Service
public class PageItemServiceImpl implements IPageItemService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PageItemServiceImpl.class);

	private IPageItemDao dao;

	@Autowired
	public PageItemServiceImpl(IPageItemDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPageItem createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPageItem entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new page history created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("page history updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IPageItem get(final Integer id) {
		final IPageItem entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all pages");
		dao.deleteAll();
	}

	@Override
	public List<IPageItem> getAll() {
		final List<IPageItem> all = dao.selectAll();
		return all;
	}


	@Override
	public long getCount(PageItemFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IPageItem> find(PageItemFilter filter) {
		return dao.find(filter);
	}
	
	  @Override
	    public void save(IPageItem... entities) {
	        Date modified = new Date();
	        for (IPageItem iPage : entities) {

	            iPage.setUpdated(modified);
	            iPage.setCreated(modified);

	        }

	        dao.save(entities);
	    }

	@Override
	public IPageItem getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}

