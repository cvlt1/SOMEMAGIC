package com.itacademy.jd2.yi.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.yi.cms.dao.api.ISiteDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.yi.cms.service.ISiteService;
@Service
public class SiteServiceImpl implements ISiteService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SiteServiceImpl.class);

    private ISiteDao dao;
    
    @Autowired
    public SiteServiceImpl(ISiteDao dao) {
        super();
        this.dao = dao;
    }

    @Override
    public ISite createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final ISite entity) {
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
    public ISite get(final Integer id) {
        final ISite entity = dao.get(id);
        return entity;
    }

    @Override
    public void delete(final Integer id) {
        dao.delete(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public List<ISite> getAll() {
        final List<ISite> all = dao.selectAll();
        return all;
    }

	@Override
	public List<ISite> find(SiteFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(SiteFilter filter) {
		return dao.getCount(filter);
	}

    @Override
    public void save(ISite... entities) {
        Date modified = new Date();
        for (ISite iSite : entities) {

            iSite.setUpdated(modified);
            iSite.setCreated(modified);

        }

        dao.save(entities);
}

	@Override
	public ISite get(String basePath) {
		return dao.getByBasePath(basePath);
	}	

}

