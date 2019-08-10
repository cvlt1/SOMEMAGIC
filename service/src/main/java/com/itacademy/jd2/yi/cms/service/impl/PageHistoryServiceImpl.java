package com.itacademy.jd2.yi.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.yi.cms.dao.api.IPageHistoryDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageHistoryFilter;
import com.itacademy.jd2.yi.cms.service.IPageHistoryService;

@Service
public class PageHistoryServiceImpl implements IPageHistoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PageHistoryServiceImpl.class);

	private IPageHistoryDao dao;

	@Autowired
	public PageHistoryServiceImpl(IPageHistoryDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPageHistory createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPageHistory entity) {
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
	public IPageHistory get(final Integer id) {
		final IPageHistory entity = dao.get(id);
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
	public List<IPageHistory> getAll() {
		final List<IPageHistory> all = dao.selectAll();
		return all;
	}


	@Override
	public long getCount(PageHistoryFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IPageHistory> find(PageHistoryFilter filter) {
		return dao.find(filter);
	}
	
	  @Override
	    public void save(IPageHistory... entities) {
	        Date modified = new Date();
	        for (IPageHistory iPage : entities) {

	            iPage.setUpdated(modified);
	            iPage.setCreated(modified);

	        }

	        dao.save(entities);
	    }

	@Override
	public IPageHistory getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
