package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.web.dto.TemplateDTO;

@Component
public class TemplateFromDTOConverter implements Function<TemplateDTO, ITemplate> {

    @Autowired
    private ITemplateService templateService;

    @Override
    public ITemplate apply(final TemplateDTO dto) {
        final ITemplate entity = templateService.createEntity();
        entity.setId(dto.getId());
        entity.setViewName(dto.getViewName());
        return entity;
    }
}
