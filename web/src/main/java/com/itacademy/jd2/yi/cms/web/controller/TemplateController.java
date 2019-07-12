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

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.web.converter.TemplateToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.TemplateDTO;

@Controller
@RequestMapping(value = "/template")
public class TemplateController {

	private ITemplateService templateService;

	private TemplateToDTOConverter toDtoConverter;

	@Autowired
	private TemplateController(ITemplateService templateService, TemplateToDTOConverter toDtoConverter) {
		super();
		this.templateService = templateService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final TemplateFilter filter = new TemplateFilter();

		final List<ITemplate> entities = templateService.find(filter);
		List<TemplateDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("template.list", models);
	}

}
