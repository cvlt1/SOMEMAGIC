//package com.itacademy.jd2.yi.cms.web.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
//import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
//import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
//import com.itacademy.jd2.yi.cms.dao.api.filter.PageFilter;
//import com.itacademy.jd2.yi.cms.service.IPageService;
//import com.itacademy.jd2.yi.cms.service.ISiteService;
//import com.itacademy.jd2.yi.cms.web.converter.PageFromDTOConverter;
//import com.itacademy.jd2.yi.cms.web.converter.PageToDTOConverter;
//import com.itacademy.jd2.yi.cms.web.dto.PageDTO;
//import com.itacademy.jd2.yi.cms.web.dto.TemplateDTO;
//import com.itacademy.jd2.yi.cms.web.dto.grid.GridStateDTO;
//@Controller
//@RequestMapping(value = "/page")
//public class PageController extends AbstractController {
//
//    @Autowired
//    private IPageService pageService;
//
////    @Autowired
////    private ISiteService siteService;
//
//    @Autowired
//    private PageFromDTOConverter fromDtoConverter;
//
//    @Autowired
//    private PageToDTOConverter toDtoConverter;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView index(final HttpServletRequest req,
//            @RequestParam(name = "page", required = false) final Integer pageNumber,
//            @RequestParam(name = "sort", required = false) final String sortColumn) {
//
//        final GridStateDTO gridState = getListDTO(req);
//        gridState.setPage(pageNumber);
//        gridState.setSort(sortColumn, "id");
//
//        final PageFilter filter = new PageFilter();
//        prepareFilter(gridState, filter);
//        gridState.setTotalCount(pageService.getCount(filter));
//
//        filter.setFetchSite(true);
//        final List<IPage> entities = pageService.find(filter);
//        List<PageDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
//
//        final Map<String, Object> models = new HashMap<>();
//        models.put("gridItems", dtos);
//        return new ModelAndView("page.list", models);
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public ModelAndView showForm() {
//        final Map<String, Object> hashMap = new HashMap<>();
//        hashMap.put("formModel", new PageDTO());
//        //loadCommonFormModels(hashMap);
//        return new ModelAndView("page.edit", hashMap);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public Object save(@Valid @ModelAttribute("formModel") final PageDTO formModel, final BindingResult result) {
//        if (result.hasErrors()) {
//            final Map<String, Object> hashMap = new HashMap<>();
//            hashMap.put("formModel", formModel);
//           // loadCommonFormModels(hashMap);
//            return new ModelAndView("page.edit", hashMap);
//        } else {
//            final IPage entity = fromDtoConverter.apply(formModel);
//            pageService.save(entity);
//            return "redirect:/page";
//        }
//    }
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
//		final IPage dbModel = pageService.get(id);
//		final PageDTO dto = toDtoConverter.apply(dbModel);
//		final Map<String, Object> hashMap = new HashMap<>();
//		hashMap.put("formModel", dto);
//		hashMap.put("readonly", true);
//
//		return new ModelAndView("page.edit", hashMap);
//	}
//
//    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
//    public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
//        final PageDTO dto = toDtoConverter.apply(pageService.get(id));
//
//        final Map<String, Object> hashMap = new HashMap<>();
//        hashMap.put("formModel", dto);
//        //loadCommonFormModels(hashMap);
//        return new ModelAndView("page.edit", hashMap);
//    }
//
//    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
//    public String delete(@PathVariable(name = "id", required = true) final Integer id) {
//        pageService.delete(id);
//        return "redirect:/page";
//    }
//
////    private void loadCommonFormModels(final Map<String, Object> hashMap) {
////        final List<ISite> sites = siteService.getAll();
////
////        /*
////         * final Map<Integer, String> brandsMap = new HashMap<>(); for (final
////         * IBrand iBrand : brands) { brandsMap.put(iBrand.getId(),
////         * iBrand.getName()); }
////         */
////
////        final Map<Integer, String> brandsMap = sites.stream()
////                .collect(Collectors.toMap(ISite::getId, ISite::getName));
////        hashMap.put("brandsChoices", brandsMap);
////
////        final Map<Integer, String> enginesMap = engineService.getAll().stream()
////                .collect(Collectors.toMap(IEngine::getId, IEngine::getTitle));
////        hashMap.put("engineChoices", enginesMap);
////    }
//
//}