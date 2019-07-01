package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;

public interface IUserAccountService {

    IUserAccount get(Integer id);

    List<IUserAccount> getAll();

<<<<<<< HEAD
    void save(IUserAccount... entities);

    void delete(Integer id);

    void deleteAll();

    IUserAccount createEntity();

=======
    void save(IUserAccount entity);

    void delete(Integer id);

    void deleteAll();

    IUserAccount createEntity();

>>>>>>> master
	long getCount(UserAccountFilter filter);

	List<IUserAccount> find(UserAccountFilter filter);

<<<<<<< HEAD
	void save(IUserAccount entity);

    //List<IBrand> find(BrandFilter filter);

    //long getCount(BrandFilter filter);
}
=======
    //List<IBrand> find(BrandFilter filter);

    //long getCount(BrandFilter filter);
}
>>>>>>> master
