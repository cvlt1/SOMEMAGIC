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

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageItemFilter;
import com.itacademy.jd2.yi.cms.service.IPageItemService;
import com.itacademy.jd2.yi.cms.web.converter.PageItemFromDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.PageItemToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.PageHistoryDTO;
import com.itacademy.jd2.yi.cms.web.dto.PageItemDTO;
import com.itacademy.jd2.yi.cms.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/pageitem")
public class PageItemController extends AbstractController {

	@Autowired
	private IPageItemService pageItemService;

//	@Autowired
//	private ISiteService siteService;

	@Autowired
	private PageItemFromDTOConverter fromDtoConverter;

	@Autowired
	private PageItemToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final PageItemFilter filter = new PageItemFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(pageItemService.getCount(filter));

		//filter.setFetchSite(true);
		final List<IPageItem> entities = pageItemService.find(filter);
		List<PageItemDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("pageItem.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new PageHistoryDTO());
		// loadCommonFormModels(hashMap);
		return new ModelAndView("pageItem.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final PageItemDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			// loadCommonFormModels(hashMap);
			return new ModelAndView("pageItem.edit", hashMap);
		} else {
			final IPageItem entity = fromDtoConverter.apply(formModel);
			pageItemService.save(entity);
			return "redirect:/pageitem";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPageItem dbModel = pageItemService.get(id);
		final PageItemDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("pageItem.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PageItemDTO dto = toDtoConverter.apply(pageItemService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		// loadCommonFormModels(hashMap);
		return new ModelAndView("pageItem.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		pageItemService.delete(id);
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

