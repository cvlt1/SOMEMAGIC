package com.itacademy.jd2.yi.cms.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.service.IPageService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.web.exception.ResourceNotFoundException;

@Controller
@RequestMapping(value = "/sites")
public class SitePageController extends AbstractController {

	@Autowired
	private ISiteService siteService;

	@Autowired
	private IPageService pageService;

	@RequestMapping(method = RequestMethod.GET, value = "/{sitePath}/{pagePath}")
	public ModelAndView index(final HttpServletRequest req,
			@PathVariable(name = "sitePath", required = true) final String sitePath,
			@PathVariable(name = "pagePath", required = true) final String pagePath) {

		// get sit
		ISite site = siteService.get(sitePath);

		if (site == null) {
			throw new ResourceNotFoundException();
		}
		IPage page = null;// pageService.get(pagePath);  //fetch all data(template, site etc)

		if (page == null) { // or if not published status
			throw new ResourceNotFoundException();
		}
		
		ITemplate template = page.getTemplate();
		String viewName = template.getJspPath(); // rename to 'viewName'
		
		
		
		List<IContentItem> pageContentItems = new ArrayList<IContentItem>(); // load items by page
		

		// render page content according to template

		final Map<String, Object> models = new HashMap<>();

		models.put("siteName", site.getName());
		
		models.put("contentItems", pageContentItems);
		return new ModelAndView(viewName, models);
	}
}
