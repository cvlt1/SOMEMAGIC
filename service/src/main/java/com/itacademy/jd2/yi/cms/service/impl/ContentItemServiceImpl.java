package com.itacademy.jd2.yi.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.yi.cms.dao.api.IContentItemDao;
import com.itacademy.jd2.yi.cms.dao.api.IPageHistoryDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.filter.ContentItemFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageHistoryFilter;
import com.itacademy.jd2.yi.cms.service.IContentItemService;
import com.itacademy.jd2.yi.cms.service.IPageHistoryService;

@Service
public class ContentItemServiceImpl implements IContentItemService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentItemServiceImpl.class);

	private IContentItemDao dao;

	@Autowired
	public ContentItemServiceImpl(IContentItemDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IContentItem createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IContentItem entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new content created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("content updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IContentItem get(final Integer id) {
		final IContentItem entity = dao.get(id);
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
	public List<IContentItem> getAll() {
		final List<IContentItem> all = dao.selectAll();
		return all;
	}


	@Override
	public long getCount(ContentItemFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IContentItem> find(ContentItemFilter filter) {
		return dao.find(filter);
	}
	
	  @Override
	    public void save(IContentItem... entities) {
	        Date modified = new Date();
	        for (IContentItem iPage : entities) {

	            iPage.setUpdated(modified);
	            iPage.setCreated(modified);

	        }

	        dao.save(entities);
	    }

	@Override
	public IContentItem getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
