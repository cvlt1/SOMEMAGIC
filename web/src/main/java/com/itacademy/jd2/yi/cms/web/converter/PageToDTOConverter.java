package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.web.dto.PageDTO;

@Component
public class PageToDTOConverter implements Function<IPage, PageDTO> {
	
	 @Override
	    public PageDTO apply(final IPage entity) {
	        final PageDTO pageDto = new PageDTO();
	        pageDto.setId(entity.getId());
	        pageDto.setPath(entity.getPath());
	        pageDto.setPageStatus(entity.getPageStatus());
	        pageDto.setPageTitle(entity.getPageTitle());
	        pageDto.setCreated(entity.getCreated());
	        pageDto.setUpdated(entity.getUpdated());

	        final ISite site = entity.getSite();
	        if (site != null) {
	            pageDto.setSiteId(site.getId());
	            pageDto.setSiteName(site.getSiteName());
	            pageDto.setSiteBasepath(site.getBasepath());
	        }
	        
	        final IUserAccount userAccount = entity.getCreator();
	        if (userAccount != null) {
	        	pageDto.setCreatorId(userAccount.getId());
	        	pageDto.setCreatorName(userAccount.getName());
	        	pageDto.setCreatorEmail(userAccount.getEmail());
	        	pageDto.setCreatorPassword(userAccount.getPassword());
	        	pageDto.setCreatorRole(userAccount.getRole());
	        	pageDto.setCreatorStatus(userAccount.getStatus());
	        }
	        final ITemplate template = entity.getTemplate();
	        if (site != null) {
	            pageDto.setTemplateId(template.getId());
	            pageDto.setJspPath(template.getJspPath());
	        }
			return pageDto;
	        
	 }

}
