package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.web.dto.ContentItemDTO;

@Component
public class ContentItemToDTOConverter implements Function<IContentItem, ContentItemDTO> {
	
	 @Override
	    public ContentItemDTO apply(final IContentItem entity) {
	        final ContentItemDTO contentItemDto = new ContentItemDTO();
	        contentItemDto.setId(entity.getId());
	        contentItemDto.setHtml(entity.getHtml());
	        contentItemDto.setTitle(entity.getTitle());
	        contentItemDto.setCreated(entity.getCreated());
	        contentItemDto.setUpdated(entity.getUpdated());

	        final ISite site = entity.getSite();
	        if (site != null) {
	        	 contentItemDto.setSiteId(site.getId());
	        	 contentItemDto.setSiteName(site.getName());
	        	 contentItemDto.setSiteBasepath(site.getBasePath());
	        }
	        
			return contentItemDto;
	        
	 }

}