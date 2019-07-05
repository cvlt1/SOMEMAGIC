package com.itacademy.jd2.yi.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.yi.cms.dao.api.ITemplateDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;
import com.itacademy.jd2.yi.cms.service.ITemplateService;


@Service
public class TemplateServiceImpl implements ITemplateService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateServiceImpl.class);

    private ITemplateDao dao;
    
    @Autowired
    public TemplateServiceImpl(ITemplateDao dao) {
        super();
        this.dao = dao;
    }

    @Override
    public ITemplate createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final ITemplate entity) {
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
    public ITemplate get(final Integer id) {
        final ITemplate entity = dao.get(id);
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
    public List<ITemplate> getAll() {
        final List<ITemplate> all = dao.selectAll();
        return all;
    }

	@Override
	public List<ITemplate> find(TemplateFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(TemplateFilter filter) {
		return dao.getCount(filter);
	}

    @Override
    public void save(ITemplate... entities) {
        Date modified = new Date();
        for (ITemplate iTemplate : entities) {

            iTemplate.setUpdated(modified);
            iTemplate.setCreated(modified);

        }

        dao.save(entities);
}	
	

   


}
