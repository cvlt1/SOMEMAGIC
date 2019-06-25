package com.itacademy.jd2.yi.cms.service.impl;


import java.util.Date;
import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.IUserAccountDao;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.yi.cms.jdbc.impl.UserAccountDaoImpl;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class UserAccountServiceImpl implements IUserAccountService {

   // private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    private IUserAccountDao dao = new UserAccountDaoImpl();

    @Override
    public IUserAccount createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IUserAccount entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            //LOGGER.info("new brand created: {}", entity);
            entity.setCreated(modifedOn);
            dao.insert(entity);
        } else {
            //LOGGER.debug("brand updated: {}", entity);
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
        //LOGGER.info("delete all brands");
        dao.deleteAll();
    }

    @Override
    public List<IUserAccount> getAll() {
        final List<IUserAccount> all = dao.selectAll();
        return all;
    }

//    @Override
//    public void save(IUserAccount... entities) {
//        Date modified = new Date();
//        for (IUserAccount iUserAccount : entities) {
//
//            iUserAccount.setUpdated(modified);
//            iUserAccount.setCreated(modified);
//
//        }
//
//        dao.save(entities);
//    }

    @Override
    public List<IUserAccount> find(UserAccountFilter filter) {
        return dao.find(filter);
    }

    @Override
    public long getCount(UserAccountFilter filter) {
        return dao.getCount(filter);
    }

}