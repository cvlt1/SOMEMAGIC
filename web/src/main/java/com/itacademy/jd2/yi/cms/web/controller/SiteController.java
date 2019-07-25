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

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.SiteFilter;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.web.converter.SiteFromDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.SiteToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.SiteDTO;
import com.itacademy.jd2.yi.cms.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/site")
public class SiteController extends AbstractController {
	@Autowired
	private ISiteService siteService;
	@Autowired
	private SiteToDTOConverter toDtoConverter;
	@Autowired
	private SiteFromDTOConverter fromDtoConverter;

	@Autowired
	private SiteController(ISiteService siteService, SiteToDTOConverter toDtoConverter,
			SiteFromDTOConverter fromDtoConverter) {
		super();
		this.siteService = siteService;
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

		final SiteFilter filter = new SiteFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(siteService.getCount(filter));

		final List<ISite> entities = siteService.find(filter);
		List<SiteDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("site.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ISite newEntity = siteService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("site.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final SiteDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "site.edit";
		} else {
			final ISite entity = fromDtoConverter.apply(formModel);
			siteService.save(entity);
			return "redirect:/site";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		siteService.delete(id);
		return "redirect:/site";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ISite dbModel = siteService.get(id);
		final SiteDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("site.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final SiteDTO dto = toDtoConverter.apply(siteService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("site.edit", hashMap);
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public ResponseEntity<SiteDTO> getCountries(@RequestParam(name = "id", required = true) final Integer id) {
		final SiteDTO dto = toDtoConverter.apply(siteService.get(id));

		return new ResponseEntity<SiteDTO>(dto, HttpStatus.OK);
	}

}

