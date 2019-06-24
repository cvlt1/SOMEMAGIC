package com.itacademy.jd2.yi.cms.service.impl;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;


public abstract class AbstractTest {
    protected IUserAccountService userAccountService = new UserAccountServiceImpl();
   // protected IModelService modelService = new ModelServiceImpl();
   // protected IEngineService engineService = new EngineServiceImpl();

    private static final Random RANDOM = new Random();

    @BeforeEach
    public void setUpMethod() {
        // clean DB recursive
        //modelService.deleteAll();
        //brandService.deleteAll();

    }

    protected String getRandomPrefix() {
        return RANDOM.nextInt(99999) + "";
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    public Random getRANDOM() {
        return RANDOM;
    }

    protected IUserAccount saveNewUserAccount() {
        final IUserAccount entity = userAccountService.createEntity();
        entity.setName("user_account" + getRandomPrefix());
        userAccountService.save(entity);
        return entity;
    }



}
