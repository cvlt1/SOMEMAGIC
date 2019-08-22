package com.itacademy.jd2.yi.cms.dao.api;

import java.util.List;



import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;

public interface IUserAccountDao extends IDao<IUserAccount, Integer> {
	
	void save1(IUserAccount... entities);
	
	List <IUserAccount> find (UserAccountFilter filter);
	
	long getCount (UserAccountFilter filter);

	IUserAccount findByNickname(String name);

				

}