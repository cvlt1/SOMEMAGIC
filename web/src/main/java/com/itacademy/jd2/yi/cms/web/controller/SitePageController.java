package com.itacademy.jd2.yi.cms.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.filter.ContentItemFilter;
import com.itacademy.jd2.yi.cms.dao.orm.impl.entity.ContentItem;
import com.itacademy.jd2.yi.cms.service.IContentItemService;
import com.itacademy.jd2.yi.cms.service.IPageService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.web.converter.ContentItemFromDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.ContentItemToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.ContentItemDTO;
import com.itacademy.jd2.yi.cms.web.exception.ResourceNotFoundException;

@Controller
@RequestMapping(value = "/sites")
public class SitePageController extends AbstractController {

	@Autowired
	private ISiteService siteService;

	@Autowired
	private IPageService pageService;
	
	@Autowired
	private IContentItemService contentItemService;
	
	@Autowired
	ContentItemToDTOConverter toDtoConverter;
	
	@Autowired
	ContentItemFromDTOConverter fromDtoConverter;
	
	@Autowired
	private SitePageController(IContentItemService contentItemService, ContentItemToDTOConverter toDtoConverter,
			ContentItemFromDTOConverter fromDtoConverter, ISiteService siteService, IPageService pageService) {
		super();
		this.siteService = siteService;
		this.contentItemService = contentItemService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
		this.pageService = pageService;

	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/{sitePath}/{pagePath}")
	public ModelAndView index(final HttpServletRequest req,
			@PathVariable(name = "sitePath", required = true) final String sitePath,
			@PathVariable(name = "pagePath", required = true) final String pagePath,
			Integer id) {

		// get sit
		ISite site = siteService.get(sitePath);

		if (site == null) {
			throw new ResourceNotFoundException();
		}
		IPage page = pageService.getFullInfo(pagePath);  //fetch all data(template, site etc)

		if (page == null || !(page.getPageStatus().equals(PageStatus.PUBLISHED))) { // or if not published status
			throw new ResourceNotFoundException();
		}
		
		ITemplate template = page.getTemplate();
		String viewName = template.getViewName(); // rename to 'viewName'
		
		final ContentItemFilter filter = new ContentItemFilter();

		final List<IContentItem> entities = contentItemService.find(filter);
		List<ContentItemDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		
		

		// render page content according to template
	
		//turn to DTO
		// render page content according to template

		final Map<String, Object> models = new HashMap<>();

		models.put("sitePath", site.getBasePath());
		models.put("contentItems", dtos);
		return new ModelAndView(viewName, models);
	}
}
