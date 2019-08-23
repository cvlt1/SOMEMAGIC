package com.itacademy.jd2.yi.cms.service.impl;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.yi.cms.dao.api.IUserAccountDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;



@Service
public class UserAccountServiceImpl implements IUserAccountService {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	 
	 
	 private String password;
	 
	 
    private IUserAccountDao dao; 
    
    
    @Autowired
    public UserAccountServiceImpl(IUserAccountDao dao) {
        super();
        this.dao = dao;
    }
    


    @Override
    public IUserAccount createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IUserAccount entity) {
        final Date modifedOn = new Date();
        
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
        	LOGGER.info("new user account created: {}", entity);
        	entity.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            entity.setCreated(modifedOn);
            
            dao.insert(entity);
        } else {
        	LOGGER.debug("user account updated: {}", entity);
            dao.update(entity);
        }
    }

    @Override
    public IUserAccount get(final Integer id) {
        final IUserAccount entity = dao.get(id);
        return entity;
    }

    @Override
    public void delete(final Integer id) {
        dao.delete(id);
    }

    @Override
    public void deleteAll() {
    	LOGGER.info("delete all user accounts");
        dao.deleteAll();
    }

    @Override
    public List<IUserAccount> getAll() {
        final List<IUserAccount> all = dao.selectAll();
        return all;
    }

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		return dao.getCount(filter);
	}

    @Override
    public void save(IUserAccount... entities) {
        Date modified = new Date();
        for (IUserAccount iUserAccount : entities) {

            iUserAccount.setUpdated(modified);
            iUserAccount.setCreated(modified);

        }

        dao.save(entities);
}



	@Override
	public IUserAccount findNickname(String name) {
		return dao.findByNickname(name);
	}







	
	







    
	

   


}