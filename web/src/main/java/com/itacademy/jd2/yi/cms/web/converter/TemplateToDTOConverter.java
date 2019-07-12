package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.web.dto.TemplateDTO;

//import com.itacademy.jd2.dz.cardealer.dao.api.entity.table.IBrand;
//import com.itacademy.jd2.dz.carsdealer.web.dto.BrandDTO;

@Component
public class TemplateToDTOConverter implements Function<ITemplate, TemplateDTO> {

    @Override
    public TemplateDTO apply(final ITemplate entity) {
        final TemplateDTO dto = new TemplateDTO();
        dto.setId(entity.getId());
        dto.setJspPath(entity.getJspPath());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }

}
