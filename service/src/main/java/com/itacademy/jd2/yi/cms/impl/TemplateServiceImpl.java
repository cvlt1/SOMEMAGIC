package com.itacademy.jd2.yi.cms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.yi.cms.dao.api.ITemplateDao;
import com.itacademy.jd2.yi.cms.dao.api.IUserAccountDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.yi.cms.jdbc.impl.TemplateDaoImpl;
import com.itacademy.jd2.yi.cms.jdbc.impl.UserAccountDaoImpl;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;
@Service
public class TemplateServiceImpl implements ITemplateService {

    private ITemplateDao dao; // = new TemplateDaoImpl();
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

