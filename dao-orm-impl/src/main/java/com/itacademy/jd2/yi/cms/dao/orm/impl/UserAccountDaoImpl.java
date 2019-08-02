package com.itacademy.jd2.yi.cms.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.yi.cms.dao.api.IUserAccountDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.yi.cms.dao.orm.entity.UserAccount;
@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

    protected UserAccountDaoImpl() {
        super(UserAccount.class);
    }

	@Override
	public IUserAccount createEntity() {
		final UserAccount userAccount = new UserAccount();
		return userAccount;
	}

	@Override
	public void save(IUserAccount... entities) {
		throw new RuntimeException ("not implemented");		
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		throw new RuntimeException ("not implemented");
		}

	@Override
	public long getCount(UserAccountFilter filter) {
		throw new RuntimeException ("not implemented");
	}

	@Override
	public IUserAccount findByNickname(String name) {
		throw new RuntimeException ("not implemented");
	}

}
