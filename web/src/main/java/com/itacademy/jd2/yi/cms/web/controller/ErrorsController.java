package com.itacademy.jd2.yi.cms.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/errors")
public class ErrorsController extends AbstractController {


    @RequestMapping(value = "/{status}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable(name = "status", required = true) final Integer status) {
        return new ModelAndView("errors."+status);
    }

}