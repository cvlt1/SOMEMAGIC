package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.web.dto.PageDTO;
import com.itacademy.jd2.yi.cms.web.dto.PageHistoryDTO;

@Component
public class PageHistoryToDTOConverter implements Function<IPageHistory, PageHistoryDTO> {
	
	 @Override
	    public PageHistoryDTO apply(final IPageHistory entity) {
	        final PageHistoryDTO pageHistoryDto = new PageHistoryDTO();
	        pageHistoryDto.setId(entity.getId());
	        pageHistoryDto.setComment(entity.getComment());
	        pageHistoryDto.setCreated(entity.getCreated());
	        pageHistoryDto.setUpdated(entity.getUpdated());
	        pageHistoryDto.setVersion(entity.getVersion());


	        final IPage page = entity.getPage();
	        if (page != null) {
	            pageHistoryDto.setPageId(page.getId());
	            pageHistoryDto.setPagePath(page.getPath());
	            pageHistoryDto.setPageTitle(page.getPageTitle());
	            pageHistoryDto.setPageStatus(page.getPageStatus());
	        }
	        
	        final IUserAccount userAccount = entity.getChangedBy();
	        if (userAccount != null) {
	        	pageHistoryDto.setChangedBy(userAccount.getId());
	        	pageHistoryDto.setUserAccountName(userAccount.getName());
	        	pageHistoryDto.setUserAccountEmail(userAccount.getEmail());
	        	pageHistoryDto.setUserAccountPassword(userAccount.getPassword());
	        	pageHistoryDto.setUserAccountRole(userAccount.getRole());
	        	pageHistoryDto.setUserAccountStatus(userAccount.getStatus());
	        }
			return pageHistoryDto;
	        
	 }

}
