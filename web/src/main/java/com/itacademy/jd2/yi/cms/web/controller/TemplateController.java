package com.itacademy.jd2.yi.cms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.filter.TemplateFilter;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.web.converter.TemplateFromDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.TemplateToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.TemplateDTO;
import com.itacademy.jd2.yi.cms.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/template")
public class TemplateController extends AbstractController {

	private ITemplateService templateService;

	private TemplateToDTOConverter toDtoConverter;

	private TemplateFromDTOConverter fromDtoConverter;

	@Autowired
	private TemplateController(ITemplateService templateService, TemplateToDTOConverter toDtoConverter,
			TemplateFromDTOConverter fromDtoConverter) {
		super();
		this.templateService = templateService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final TemplateFilter filter = new TemplateFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(templateService.getCount(filter));

		final List<ITemplate> entities = templateService.find(filter);
		List<TemplateDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("template.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ITemplate newEntity = templateService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("template.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final TemplateDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "template.edit";
		} else {
			final ITemplate entity = fromDtoConverter.apply(formModel);
			templateService.save(entity);
			return "redirect:/template";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		templateService.delete(id);
		return "redirect:/template";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITemplate dbModel = templateService.get(id);
		final TemplateDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("template.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TemplateDTO dto = toDtoConverter.apply(templateService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("template.edit", hashMap);
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public ResponseEntity<TemplateDTO> getCountries(@RequestParam(name = "id", required = true) final Integer id) {
		final TemplateDTO dto = toDtoConverter.apply(templateService.get(id));

		return new ResponseEntity<TemplateDTO>(dto, HttpStatus.OK);
	}

}
