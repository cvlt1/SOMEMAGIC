package com.itacademy.jd2.yi.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.yi.cms.dao.api.IPageDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
import com.itacademy.jd2.yi.cms.service.IPageService;

@Service
public class PageServiceImpl implements IPageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PageServiceImpl.class);

	private IPageDao dao;

	@Autowired
	public PageServiceImpl(IPageDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPage createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPage entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new page created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("page updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IPage get(final Integer id) {
		final IPage entity = dao.get(id);
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
	public List<IPage> getAll() {
		final List<IPage> all = dao.selectAll();
		return all;
	}


	@Override
	public long getCount(PageFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IPage> find(PageFilter filter) {
		return dao.find(filter);
	}
	
	  @Override
	    public void save(IPage... entities) {
	        Date modified = new Date();
	        for (IPage iPage : entities) {

	            iPage.setUpdated(modified);
	            iPage.setCreated(modified);

	        }

	        dao.save(entities);
	    }

	@Override
	public IPage getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
