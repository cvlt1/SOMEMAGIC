package com.itacademy.jd2.yi.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;

public interface IUserAccountService {

    IUserAccount get(Integer id);

    List<IUserAccount> getAll();
    
    @Transactional
    void save(IUserAccount... entities);
    
    @Transactional
    void delete(Integer id);
    
    @Transactional
    void deleteAll();
   
    IUserAccount createEntity();

	long getCount(UserAccountFilter filter);
	
	List<IUserAccount> find(UserAccountFilter filter);
	
//	@Transactional
//	void save(IUserAccount entity);
	
	IUserAccount findNickname(String name);
	
	@Transactional
	void save(IUserAccount entity, String password);

	


}