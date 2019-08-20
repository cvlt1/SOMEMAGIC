package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.service.IPageHistoryService;
import com.itacademy.jd2.yi.cms.service.IPageService;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;
import com.itacademy.jd2.yi.cms.web.dto.PageHistoryDTO;

@Component
public class PageHistoryFromDTOConverter implements Function<PageHistoryDTO, IPageHistory> {

    @Autowired
    private IPageHistoryService pageHistoryService;
    @Autowired
    private IPageService pageService;
    @Autowired
    private IUserAccountService userAccountService;

    @Override
    public IPageHistory apply(final PageHistoryDTO dto) {
        final IPageHistory entity = pageHistoryService.createEntity();
        entity.setId(dto.getId());
        entity.setComment(dto.getComment());
        entity.setVersion(dto.getVersion());

        
        final IPage page = pageService.createEntity();
        page.setId(dto.getPageId());
        entity.setPage(page);
        
        final IUserAccount userAccount = userAccountService.createEntity();
        userAccount.setId(dto.getChangedBy());
        entity.setChangedBy(userAccount);
        
        return entity;
        
        
    }
    
}
