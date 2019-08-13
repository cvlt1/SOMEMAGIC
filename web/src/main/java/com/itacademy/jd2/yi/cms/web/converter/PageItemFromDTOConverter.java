package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.service.IContentItemService;
import com.itacademy.jd2.yi.cms.service.IPageHistoryService;
import com.itacademy.jd2.yi.cms.service.IPageItemService;
import com.itacademy.jd2.yi.cms.service.IPageService;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;
import com.itacademy.jd2.yi.cms.web.dto.PageHistoryDTO;
import com.itacademy.jd2.yi.cms.web.dto.PageItemDTO;

@Component
public class PageItemFromDTOConverter implements Function<PageItemDTO, IPageItem> {

    @Autowired
    private IPageItemService pageItemService;
    @Autowired
    private IPageService pageService;
    @Autowired
    private IContentItemService contentItemService;

    @Override
    public IPageItem apply(final PageItemDTO dto) {
        final IPageItem entity = pageItemService.createEntity();
        entity.setId(dto.getId());
        entity.setPosition(dto.getPosition());
        
        final IPage page = pageService.createEntity();
        page.setId(dto.getSiteId());
        entity.setPage(page);
        
        final IContentItem contentItem = contentItemService.createEntity();
        contentItem.setId(dto.getContentItemId());
        entity.setContentItem(contentItem);
        
        return entity;
        
        
    }
    
}
