package com.itacademy.jd2.yi.cms.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.ISiteDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.Site;


@Repository
public class SiteDaoImpl  extends AbstractDaoImpl<ISite, Integer> implements ISiteDao {

    protected SiteDaoImpl() {
        super(Site.class);
    }

	@Override
	public ISite createEntity() {
		final Site site = new Site();
		return site;
	}

	@Override
	public void save(ISite... entities) {
		throw new RuntimeException ("not implemented");		
	}

	@Override
	public List<ISite> find(SiteFilter filter) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public long getCount(SiteFilter filter) {
		throw new RuntimeException ("not implemented");
	}

}
