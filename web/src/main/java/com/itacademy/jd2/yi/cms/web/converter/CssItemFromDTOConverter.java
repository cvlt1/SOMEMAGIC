package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.service.ICssItemService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.web.dto.CssItemDTO;
@Component
public class CssItemFromDTOConverter implements Function<CssItemDTO, ICssItem> {
	
    @Autowired
    private ICssItemService cssItemService;
    
    @Autowired
    private ISiteService siteService;
    
    @Override
    public ICssItem apply(final CssItemDTO dto) {
        final ICssItem entity = cssItemService.createEntity();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        
        
        final ISite site = siteService.createEntity();
        site.setId(dto.getSiteId());
        entity.setSite(site);
        
        return entity;
    }
  

}
