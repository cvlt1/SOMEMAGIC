package com.itacademy.jd2.yi.cms.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.IPageDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
import com.itacademy.jd2.yi.cms.dao.orm.entity.Page;
@Repository
public class PageDaoImpl extends AbstractDaoImpl<IPage, Integer> implements IPageDao {

    protected PageDaoImpl() {
        super (Page.class);
    }
    
	@Override
	public IPage createEntity() {
		final Page page = new Page();
		return page;
	}

	@Override
	public void save(IPage... entities) {
		throw new RuntimeException ("not implemented");		
	}

	@Override
	public List<IPage> find(PageFilter filter) {
		throw new RuntimeException ("not implemented");
		}

	@Override
	public long getCount(PageFilter filter) {
		throw new RuntimeException ("not implemented");
	}

}
