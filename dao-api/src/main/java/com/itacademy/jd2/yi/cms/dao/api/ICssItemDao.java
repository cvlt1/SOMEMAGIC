package com.itacademy.jd2.yi.cms.dao.api;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.CssItemFilter;

public interface ICssItemDao extends IDao<ICssItem, Integer> {

	//void save(CssItemFilter... entities);

    List<ICssItem> find(CssItemFilter filter);

    long getCount(CssItemFilter filter);
}
