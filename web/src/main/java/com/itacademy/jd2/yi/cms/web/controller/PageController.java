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
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPageItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
import com.itacademy.jd2.yi.cms.dao.api.filter.PageItemFilter;
import com.itacademy.jd2.yi.cms.service.IContentItemService;
import com.itacademy.jd2.yi.cms.service.IPageItemService;
import com.itacademy.jd2.yi.cms.service.IPageService;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;
import com.itacademy.jd2.yi.cms.web.converter.ContentItemToDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.PageFromDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.PageItemToDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.PageToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.ContentItemDTO;
import com.itacademy.jd2.yi.cms.web.dto.PageDTO;
import com.itacademy.jd2.yi.cms.web.dto.PageItemDTO;
import com.itacademy.jd2.yi.cms.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/page")
public class PageController extends AbstractController {

	@Autowired
	private IPageService pageService;

	@Autowired
	private ISiteService siteService;

	@Autowired
	private ITemplateService templateService;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private IContentItemService contentItemService;

	@Autowired
	private IPageItemService pageItemService;

	@Autowired
	private PageFromDTOConverter fromDtoConverter;

	@Autowired
	private PageToDTOConverter toDtoConverter;

	@Autowired
	private ContentItemToDTOConverter contentItemToDTOConverter;
	
	
	@Autowired
	private PageItemToDTOConverter  pageItemToDTOConverter;


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final PageFilter filter = new PageFilter();
		prepareFilter(gridState, filter);
		gridState.setTotalCount(pageService.getCount(filter));

		filter.setFetchSite(true);
		final List<IPage> entities = pageService.find(filter);
		List<PageDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("page.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", new PageDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("page.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final PageDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("page.edit", hashMap);
		} else {
			final IPage entity = fromDtoConverter.apply(formModel);
			pageService.save(entity);
			return "redirect:/page";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPage dbModel = pageService.get(id);
		final PageDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("page.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PageDTO dto = toDtoConverter.apply(pageService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("page.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/items", method = RequestMethod.GET)
	public ModelAndView items(@PathVariable(name = "id", required = true) final Integer id) {
		IPage page = pageService.getFullInfo(id);
		final PageDTO dto = toDtoConverter.apply(page);

		List<? extends IContentItem> applicableItems = contentItemService.getApplicableItems(id,
				page.getSite().getId());

		List<ContentItemDTO> applicableItemsDtos = applicableItems.stream().map(contentItemToDTOConverter)
				.collect(Collectors.toList());
		
		PageItemFilter filter = new PageItemFilter();
		filter.setPageId(id);
		List<IPageItem> selectedItems = pageItemService.find(filter);
		
		
		List<PageItemDTO> selectedItemsDtos = selectedItems.stream().map(pageItemToDTOConverter)
				.collect(Collectors.toList());

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("page", dto);
		hashMap.put("applicableItems", applicableItemsDtos);
		hashMap.put("selectedItems", selectedItemsDtos);
		return new ModelAndView("page.items", hashMap);
	}
	
	
	
	@RequestMapping(value = "/{pageId}/items/{itemId}/delete", method = RequestMethod.GET)
	public String deleteItem(@PathVariable(name = "pageId", required = true) final Integer pageId,
			@PathVariable(name = "itemId", required = true) final Integer itemId) {

		pageItemService.delete(itemId);

		return String.format("redirect:/page/%s/items", pageId);
	}
	
	@RequestMapping(value = "/{id}/items/refresh", method = RequestMethod.GET)
	public ModelAndView refresh(@PathVariable(name = "id", required = true) final Integer id) {
		IPage page = pageService.getFullInfo(id);
		final PageDTO dto = toDtoConverter.apply(page);
		
		//IPageItem item = pageItemService.refreshItemPositions();

		List<? extends IContentItem> applicableItems = contentItemService.getApplicableItems(id,
				page.getSite().getId());

		List<ContentItemDTO> applicableItemsDtos = applicableItems.stream().map(contentItemToDTOConverter)
				.collect(Collectors.toList());
		
		PageItemFilter filter = new PageItemFilter();
		filter.setPageId(id);
		List<IPageItem> selectedItems = pageItemService.find(filter);
		
		
		List<PageItemDTO> selectedItemsDtos = selectedItems.stream().map(pageItemToDTOConverter)
				.collect(Collectors.toList());

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("page", dto);
		hashMap.put("applicableItems", applicableItemsDtos);
		hashMap.put("selectedItems", selectedItemsDtos);
		return new ModelAndView("page.items", hashMap);
	}
	
	
	@RequestMapping(value = "/{pageId}/items/{itemId}/add2page", method = RequestMethod.GET)
	public String addItemToPage(@PathVariable(name = "pageId", required = true) final Integer pageId,
			@PathVariable(name = "itemId", required = true) Integer itemId) {

		IPageItem entity = pageItemService.createEntity();
		
		IPage page = pageService.createEntity();
		page.setId(pageId);

		IContentItem contentItem = contentItemService.createEntity();
		contentItem.setId(itemId);

		entity.setPage(page);
		entity.setContentItem(contentItem);
		entity.setPosition(pageItemService.getNextPosition(pageId));
		
		pageItemService.save(entity);

		return String.format("redirect:/page/%s/items", pageId);
		
		
	}
	


	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		pageService.delete(id);
		return "redirect:/page";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<ISite> sites = siteService.getAll();
		final List<ITemplate> templates = templateService.getAll();
		final List<IUserAccount> userAccounts = userAccountService.getAll();

		final Map<Integer, String> sitesMap = sites.stream()
				.collect(Collectors.toMap(ISite::getId, ISite::getSiteName));
		hashMap.put("sitesChoices", sitesMap);

		final Map<Integer, String> templatesMap = templates.stream()
				.collect(Collectors.toMap(ITemplate::getId, ITemplate::getViewName));
		hashMap.put("templatesChoices", templatesMap);

		final Map<Integer, String> userAccountMap = userAccounts.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getName));
		hashMap.put("uAccChoices", userAccountMap);

	}

}