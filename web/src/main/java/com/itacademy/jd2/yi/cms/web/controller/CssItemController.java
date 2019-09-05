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

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.CssItemFilter;
import com.itacademy.jd2.yi.cms.service.ICssItemService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.web.converter.CssItemFromDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.CssItemToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.CssItemDTO;
import com.itacademy.jd2.yi.cms.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/cssitem")
public class CssItemController extends AbstractController {

	@Autowired
	private ICssItemService cssItemService;

	@Autowired
	private ISiteService siteService;

	@Autowired
	private CssItemFromDTOConverter fromDtoConverter;

	@Autowired
	private CssItemToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final CssItemFilter filter = new CssItemFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(cssItemService.getCount(filter));

		final List<ICssItem> entities = cssItemService.find(filter);
		List<CssItemDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("cssItem.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new CssItemDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("cssItem.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final CssItemDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("cssItem.edit", hashMap);
		} else {
			final ICssItem entity = fromDtoConverter.apply(formModel);
			cssItemService.save(entity);
			return "redirect:/cssitem";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICssItem dbModel = cssItemService.getFullInfo(id);
		final CssItemDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("cssItem.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CssItemDTO dto = toDtoConverter.apply(cssItemService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("cssItem.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		cssItemService.delete(id);
		return "redirect:/cssitem";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
        final List<ISite> sites = siteService.getAll();

        final Map<Integer, String> sitesMap = sites.stream()
                .collect(Collectors.toMap(ISite::getId, ISite::getSiteName));
        hashMap.put("sitesChoices", sitesMap);

    }

}

