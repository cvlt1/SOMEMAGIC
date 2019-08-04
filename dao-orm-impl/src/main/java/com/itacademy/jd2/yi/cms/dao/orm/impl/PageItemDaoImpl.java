package com.itacademy.jd2.yi.cms.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.IPageItemDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageItemFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.PageItem;

@Repository
public class PageItemDaoImpl extends AbstractDaoImpl<IPageItem, Integer> implements IPageItemDao {

    protected PageItemDaoImpl() {
        super (PageItem.class);
    }
    
	@Override
	public IPageItem createEntity() {
		final PageItem pageItem = new PageItem();
		return pageItem;
	}

	@Override
	public List<IPageItem> find(PageItemFilter filter) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public long getCount(PageItemFilter filter) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public void save(IPageItem... entities) {
		throw new RuntimeException("not implemented1");
		
	}
	
	//TODO add save method
	

}