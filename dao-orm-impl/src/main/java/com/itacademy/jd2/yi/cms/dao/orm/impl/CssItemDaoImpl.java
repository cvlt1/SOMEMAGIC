package com.itacademy.jd2.yi.cms.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.ICssItemDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.CssItemFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.CssItem;
@Repository
public class CssItemDaoImpl extends AbstractDaoImpl<ICssItem, Integer> implements ICssItemDao {

    protected CssItemDaoImpl() {
        super (CssItem.class);
    }
    
	@Override
	public ICssItem createEntity() {
		final CssItem cssItem = new CssItem();
		return cssItem;
	}

	@Override
	public List<ICssItem> find(CssItemFilter filter) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public long getCount(CssItemFilter filter) {
		throw new RuntimeException ("not implemented");
	}
	
	//TODO add save method
	

}
