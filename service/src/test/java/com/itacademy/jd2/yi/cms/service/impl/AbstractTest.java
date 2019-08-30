package com.itacademy.jd2.yi.cms.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.yi.cms.dao.api.entity.enums.UserStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IBaseEntity;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.service.IContentItemService;
import com.itacademy.jd2.yi.cms.service.ICssItemService;
import com.itacademy.jd2.yi.cms.service.IPageService;
//import com.itacademy.jd2.yi.cms.service.ICssItemService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;
@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {
	
	@Autowired
    protected IUserAccountService userAccountService;
	
	@Autowired
    protected ITemplateService templateService;
	
	@Autowired
    protected ISiteService siteService;
	
	@Autowired
	protected ICssItemService cssItemService;
	
	@Autowired
	protected IPageService pageService;
	
	@Autowired
	protected IContentItemService contentItemService;
//	@Autowired
//	protected IPageService pageService;
	
//	@Autowired
//	protected ICssItemService cssItemService;
	
    private static final Random RANDOM = new Random();

//    @BeforeEach
//    public void setUpMethod() {
//        // clean DB recursive
//        userAccountService.deleteAll();
//        templateService.deleteAll();
//        siteService.deleteAll();
//
//    }

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
        entity.setViewName("D:\\templates\\" + getRandomPrefix() + ".jsp");
        templateService.save(entity);
        return entity;
    }
    
    protected ISite saveNewSite() {
        final ISite entity = siteService.createEntity();
        entity.setName("sitename" + getRandomPrefix());
        entity.setBasePath("somepath" + getRandomPrefix() + ".com");
        siteService.save(entity);
        return entity;
    }

    protected ICssItem saveNewCssItem() {
        final ICssItem entity = cssItemService.createEntity();
        entity.setContent("content-" + getRandomPrefix());
        entity.setSite(saveNewSite());
        cssItemService.save(entity);
        return entity;
    }
    
    protected IPage saveNewPage() {
    	final IPage entity = pageService.createEntity();
    	entity.setSite(saveNewSite());
    	entity.setTemplate(saveNewTemplate());
    	entity.setPath("path-" + getRandomPrefix());
    	entity.setPageStatus(PageStatus.EDITED);
    	entity.setCreator(saveNewUserAccount());
    	entity.setPageTitle("title-" + getRandomPrefix());
    	pageService.save(entity);
		return entity;
    	
    }
    
    
    protected IBaseEntity verifyOrderById(List<? extends IBaseEntity> ascBrands, boolean ascending) {
    	IBaseEntity previousEntity = null;
    	for (IBaseEntity entity : ascBrands) {
    	if (previousEntity == null) {
    		previousEntity = entity;
    	continue;
    	}
    	if (ascending) {
    	assertTrue(previousEntity.getId().intValue() < entity.getId().intValue());
    	} else {
    	assertTrue(previousEntity.getId().intValue() > entity.getId().intValue());
    	}
    	previousEntity = entity;
    	}
    	return previousEntity;
    	}
    



}