package com.itacademy.jd2.yi.cms.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
import com.itacademy.jd2.yi.cms.service.IPageService;
import com.itacademy.jd2.yi.cms.web.converter.PageToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.PageDTO;

@Controller
@RequestMapping(value = "/page")
public class PageController {

	private IPageService pageService;

	private PageToDTOConverter toDtoConverter;

	@Autowired
	private PageController(IPageService pageService, PageToDTOConverter toDtoConverter) {
		super();
		this.pageService = pageService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final PageFilter filter = new PageFilter();

		final List<IPage> entities = pageService.find(filter);
		List<PageDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("page.list", models);
	}

}

