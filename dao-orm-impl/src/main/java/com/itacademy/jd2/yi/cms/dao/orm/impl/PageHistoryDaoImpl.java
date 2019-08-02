package com.itacademy.jd2.yi.cms.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.IPageHistoryDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageHistoryFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.PageHistory;
@Repository
public class PageHistoryDaoImpl extends AbstractDaoImpl<IPageHistory, Integer> implements IPageHistoryDao {

    protected PageHistoryDaoImpl() {
        super (PageHistory.class);
    }

	@Override
	public IPageHistory createEntity() {
        final PageHistory pageHistory = new PageHistory();
        return pageHistory;
	}

	@Override
	public void save(IPageHistory... entities) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public List<IPageHistory> find(PageHistoryFilter filter) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public long getCount(PageHistoryFilter filter) {
		throw new RuntimeException ("not implemented");
	}

}
