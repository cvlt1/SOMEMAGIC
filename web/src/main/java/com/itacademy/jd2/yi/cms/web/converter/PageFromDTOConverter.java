package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.service.IPageService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;
import com.itacademy.jd2.yi.cms.web.dto.PageDTO;

@Component
public class PageFromDTOConverter implements Function<PageDTO, IPage> {

    @Autowired
    private IPageService pageService;
    @Autowired
    private ISiteService siteService;
    @Autowired
    private ITemplateService templateService;
    @Autowired
    private IUserAccountService userAccountService;

    @Override
    public IPage apply(final PageDTO dto) {
        final IPage entity = pageService.createEntity();
        entity.setId(dto.getId());
        entity.setPath(dto.getPath());
        entity.setPageStatus(dto.getPageStatus());
        entity.setPageTitle(dto.getPageTitle());
        
        final ISite site = siteService.createEntity();
        site.setId(dto.getSiteId());
        entity.setSite(site);
        
        final ITemplate template = templateService.createEntity();
        template.setId(dto.getTemplateId());
        entity.setTemplate(template);
        
        final IUserAccount userAccount = userAccountService.createEntity();
        userAccount.setId(dto.getCreatorId());
        entity.setCreator(userAccount);
        
        return entity;
        
        
    }
    
}
