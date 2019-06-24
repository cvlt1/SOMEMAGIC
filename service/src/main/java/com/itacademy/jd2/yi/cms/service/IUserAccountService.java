package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;

public interface IUserAccountService {

	IUserAccount get(Integer id);

	List<IUserAccount> getAll();

	void save(IUserAccount entity);

	void delete(Integer id);

	void deleteAll();

	IUserAccount createEntity();

	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);

}
