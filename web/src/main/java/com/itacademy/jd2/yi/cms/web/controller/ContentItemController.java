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

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.filter.ContentItemFilter;
import com.itacademy.jd2.yi.cms.service.IContentItemService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.web.converter.ContentItemFromDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.ContentItemToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.ContentItemDTO;
import com.itacademy.jd2.yi.cms.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/contentitem")
public class ContentItemController extends AbstractController {

	@Autowired
	private IContentItemService contentItemService;

	@Autowired
	private ISiteService siteService;

	@Autowired
	private ContentItemFromDTOConverter fromDtoConverter;

	@Autowired
	private ContentItemToDTOConverter toDtoConverter;
	
	
	@Autowired
	private ContentItemController(IContentItemService contentItemService, ContentItemToDTOConverter toDtoConverter,
			ContentItemFromDTOConverter fromDtoConverter) {
		super();
		this.contentItemService = contentItemService;
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

		final ContentItemFilter filter = new ContentItemFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(contentItemService.getCount(filter));

		final List<IContentItem> entities = contentItemService.find(filter);
		List<ContentItemDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("contentItem.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new ContentItemDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("contentItem.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final ContentItemDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("contentItem.edit", hashMap);
		} else {
			final IContentItem entity = fromDtoConverter.apply(formModel);
			contentItemService.save(entity);
			return "redirect:/contentitem";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IContentItem dbModel = contentItemService.get(id);
		final ContentItemDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("contentItem.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ContentItemDTO dto = toDtoConverter.apply(contentItemService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("contentItem.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		contentItemService.delete(id);
		return "redirect:/contentitem";
	}

    private void loadCommonFormModels(final Map<String, Object> hashMap) {

        final Map<Integer, String> contentMap = siteService.getAll().stream()
                .collect(Collectors.toMap(ISite::getId, ISite::getName));

        hashMap.put("sitesChoices", contentMap);

    }

}


