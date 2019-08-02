package com.itacademy.jd2.yi.cms.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.ITemplateDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;
import com.itacademy.jd2.yi.cms.dao.orm.entity.Template;



@Repository
public class TemplateDaoImpl extends AbstractDaoImpl<ITemplate, Integer> implements ITemplateDao {

    protected TemplateDaoImpl() {
        super(Template.class);
    }

    @Override
    public ITemplate createEntity() {
        final Template template = new Template();
        return template;
    }

	@Override
	public void save(ITemplate... entities) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public List<ITemplate> find(TemplateFilter filter) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public long getCount(TemplateFilter filter) {
		throw new RuntimeException ("not implemented");
	}

}