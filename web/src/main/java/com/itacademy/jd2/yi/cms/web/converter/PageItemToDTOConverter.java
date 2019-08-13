package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.web.dto.PageHistoryDTO;
import com.itacademy.jd2.yi.cms.web.dto.PageItemDTO;

@Component
public class PageItemToDTOConverter implements Function<IPageItem, PageItemDTO> {
	
	 @Override
	    public PageItemDTO apply(final IPageItem entity) {
	        final PageItemDTO pageItemDto = new PageItemDTO();
	        pageItemDto.setId(entity.getId());
	        pageItemDto.setPosition(entity.getPosition());
	        pageItemDto.setCreated(entity.getCreated());
	        pageItemDto.setUpdated(entity.getUpdated());

	        final IPage page = entity.getPage();
	        if (page != null) {
	            pageItemDto.setPageId(page.getId());
	            pageItemDto.setPagePath(page.getPath());
	            pageItemDto.setPageTitle(page.getPageTitle());
	            pageItemDto.setPageStatus(page.getPageStatus());
	        }
	        
	        final IContentItem contentItem = entity.getContentItem();
	        if (contentItem != null) {
	        	pageItemDto.setContentItemId(contentItem.getId());
	        	pageItemDto.setHtml(contentItem.getHtml());
	        	pageItemDto.setContentItemTitle(contentItem.getTitle());
	        }
			return pageItemDto;
	        
	 }

}
