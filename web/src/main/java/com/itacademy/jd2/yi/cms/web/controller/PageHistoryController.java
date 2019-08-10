package com.itacademy.jd2.yi.cms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageHistory;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageHistoryFilter;
import com.itacademy.jd2.yi.cms.service.IPageHistoryService;
import com.itacademy.jd2.yi.cms.web.converter.PageHistoryFromDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.PageHistoryToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.PageDTO;
import com.itacademy.jd2.yi.cms.web.dto.PageHistoryDTO;
import com.itacademy.jd2.yi.cms.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/pagehistory")
public class PageHistoryController extends AbstractController {

	@Autowired
	private IPageHistoryService pageHistoryService;

//	@Autowired
//	private ISiteService siteService;

	@Autowired
	private PageHistoryFromDTOConverter fromDtoConverter;

	@Autowired
	private PageHistoryToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final PageHistoryFilter filter = new PageHistoryFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(pageHistoryService.getCount(filter));

		//filter.setFetchSite(true);
		final List<IPageHistory> entities = pageHistoryService.find(filter);
		List<PageHistoryDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("pageHistory.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new PageDTO());
		// loadCommonFormModels(hashMap);
		return new ModelAndView("pageHistory.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final PageHistoryDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			// loadCommonFormModels(hashMap);
			return new ModelAndView("pageHistory.edit", hashMap);
		} else {
			final IPageHistory entity = fromDtoConverter.apply(formModel);
			pageHistoryService.save(entity);
			return "redirect:/pagehistory";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPageHistory dbModel = pageHistoryService.get(id);
		final PageHistoryDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("pageHistory.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PageHistoryDTO dto = toDtoConverter.apply(pageHistoryService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		// loadCommonFormModels(hashMap);
		return new ModelAndView("pageHistory.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		pageHistoryService.delete(id);
		return "redirect:/pagehistory";
	}

//	private void loadCommonFormModels(final Map<String, Object> hashMap) {
//		final List<ISite> sites = siteService.getAll();
//
//		final Map<Integer, String> sitesMap = new HashMap<>();
//		for (final ISite iSite : sites) {
//			sitesMap.put(iSite.getId(), iSite.getSiteName());
//		}
//
//		final Map<Integer, String> sitesMap = sites.stream().collect(Collectors.toMap(ISite::getId, ISite::getName));
//		hashMap.put("siteChoices", sitesMap);
//
////        final Map<Integer, String> enginesMap = engineService.getAll().stream()
////                .collect(Collectors.toMap(IEngine::getId, IEngine::getTitle));
////        hashMap.put("engineChoices", enginesMap);
//	}

}
