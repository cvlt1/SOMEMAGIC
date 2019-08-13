package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.service.IContentItemService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.web.dto.ContentItemDTO;
@Component
public class ContentItemFromDTOConverter implements Function<ContentItemDTO, IContentItem> {
	
    @Autowired
    private IContentItemService contentItemService;
    
    @Autowired
    private ISiteService siteService;
    
    @Override
    public IContentItem apply(final ContentItemDTO dto) {
        final IContentItem entity = contentItemService.createEntity();
        entity.setId(dto.getId());
        entity.setHtml(dto.getHtml());
        entity.setTitle(dto.getContentItemTitle());
        
        
        final ISite site = siteService.createEntity();
        site.setId(dto.getSiteId());
        entity.setSiteId(site);
        
        return entity;
    }
  

}
