package com.itacademy.jd2.yi.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.yi.cms.dao.api.ICssItemDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.CssItemFilter;
import com.itacademy.jd2.yi.cms.service.ICssItemService;




@Service
public class CssItemServiceImpl implements ICssItemService {

	private ICssItemDao dao;

	@Autowired
	public CssItemServiceImpl(ICssItemDao dao) {
		super();
		this.dao = dao;
	}

//	@Override
//	public IModelInfo createInfoEntity() {
//		return modelInfoDao.createEntity();
//	}

	@Override
	public ICssItem createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICssItem entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public ICssItem get(final Integer id) {
		final ICssItem entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		// remove all references
		final ICssItem iCssItem = dao.get(id);
		dao.update(iCssItem);

		dao.delete(id);
}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<ICssItem> getAll() {
		final List<ICssItem> all = dao.selectAll();
		return all;
	}

//	@Override
//	public ICssItem getFullInfo(final Integer id) {
//		final ICssItem entity = dao.getFullInfo(id);
//		return entity;
//	}

//	@Override
//	public void save(final ICssItem entity) {
//		final Date modifiedDate = new Date();
//		entity.setUpdated(modifiedDate);
//
//		if (entity.getId() == null) {
//			entity.setCreated(modifiedDate);
//			dao.insert(entity);
//
//
//		} else {
//			dao.update(entity);
//		}
//	}

	@Override
	public long getCount(CssItemFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<ICssItem> find(CssItemFilter filter) {
		return dao.find(filter);
	}

}
