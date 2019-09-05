package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.web.dto.CssItemDTO;

@Component
public class CssItemToDTOConverter implements Function<ICssItem, CssItemDTO> {
	
	 @Override
	    public CssItemDTO apply(final ICssItem entity) {
	        final CssItemDTO cssItemDto = new CssItemDTO();
	        cssItemDto.setId(entity.getId());
	        cssItemDto.setContent(entity.getContent());
	        cssItemDto.setCreated(entity.getCreated());
	        cssItemDto.setUpdated(entity.getUpdated());

	        final ISite site = entity.getSite();
	        if (site != null) {
	        	 cssItemDto.setSiteId(site.getId());
	        	 cssItemDto.setSiteName(site.getSiteName());
	        	 cssItemDto.setSiteBasepath(site.getBasePath());
	        }
	        
			return cssItemDto;
	        
	 }

}
