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

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.yi.cms.service.IUserAccountService;
import com.itacademy.jd2.yi.cms.web.converter.UserAccountFromDTOConverter;
import com.itacademy.jd2.yi.cms.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.yi.cms.web.dto.UserAccountDTO;
import com.itacademy.jd2.yi.cms.web.dto.grid.GridStateDTO;


@Controller
@RequestMapping(value = "/useraccount")
public class UserAccountController extends AbstractController {
	@Autowired
    private IUserAccountService userAccountService;
	@Autowired
    private UserAccountToDTOConverter toDtoConverter;
	@Autowired
    private UserAccountFromDTOConverter fromDtoConverter;

    @Autowired
    private UserAccountController(IUserAccountService userAccountService,
    		UserAccountToDTOConverter toDtoConverter, UserAccountFromDTOConverter fromDtoConverter) {
        super();
        this.userAccountService = userAccountService;
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

        final UserAccountFilter filter = new UserAccountFilter();
        prepareFilter(gridState, filter);

        final List<IUserAccount> entities = userAccountService.find(filter);
        List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter)
                .collect(Collectors.toList());
        gridState.setTotalCount(userAccountService.getCount(filter));

        final Map<String, Object> models = new HashMap<>();
        models.put("gridItems", dtos);
        return new ModelAndView("useraccount.list", models);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm() {
        final Map<String, Object> hashMap = new HashMap<>();
        final IUserAccount newEntity = userAccountService.createEntity();
        hashMap.put("formModel", toDtoConverter.apply(newEntity));

        return new ModelAndView("useraccount.edit", hashMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("formModel") final UserAccountDTO formModel,
            final BindingResult result) {
        if (result.hasErrors()) {
            return "useraccount.edit";
        } else {
            final IUserAccount entity = fromDtoConverter.apply(formModel);
            userAccountService.save(entity);
            return "redirect:/useraccount";
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id", required = true) final Integer id) {
    	userAccountService.delete(id);
        return "redirect:/useraccount";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewDetails(
            @PathVariable(name = "id", required = true) final Integer id) {
        final IUserAccount dbModel = userAccountService.get(id);
        final UserAccountDTO dto = toDtoConverter.apply(dbModel);
        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);
        hashMap.put("readonly", true);

        return new ModelAndView("useraccount.edit", hashMap);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(
            @PathVariable(name = "id", required = true) final Integer id) {
        final UserAccountDTO dto = toDtoConverter.apply(userAccountService.get(id));

        final Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);

        return new ModelAndView("useraccount.edit", hashMap);
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public ResponseEntity<UserAccountDTO> getCountries(
            @RequestParam(name = "id", required = true) final Integer id) {
        final UserAccountDTO dto = toDtoConverter.apply(userAccountService.get(id));

        return new ResponseEntity<UserAccountDTO>(dto, HttpStatus.OK);
    }
}
