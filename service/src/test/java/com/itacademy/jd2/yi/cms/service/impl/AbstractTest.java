package com.itacademy.jd2.yi.cms.service.impl;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.impl.TemplateServiceImpl;
import com.itacademy.jd2.yi.cms.impl.UserAccountServiceImpl;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;

public abstract class AbstractTest {
    protected IUserAccountService userAccountService = new UserAccountServiceImpl();
    protected ITemplateService templateService = new TemplateServiceImpl();
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
        entity.setEmail("user_account@" + getRandomPrefix() + "gmail.com");
        entity.setPassword("userpassword" + getRandomPrefix());
        entity.setRole(UserRole.BASIC);
        entity.setStatus(UserStatus.ACTIVE);
        userAccountService.save(entity);
        return entity;
    }

    protected ITemplate saveNewTemplate() {
        final ITemplate entity = templateService.createEntity();
        entity.setJspPath("D:\\templates\\" + getRandomPrefix() + ".jsp");

        templateService.save(entity);
        return entity;
    }


}
