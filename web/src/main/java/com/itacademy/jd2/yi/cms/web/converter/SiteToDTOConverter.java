package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.web.dto.PageDTO;
import com.itacademy.jd2.yi.cms.web.dto.SiteDTO;
import com.itacademy.jd2.yi.cms.web.dto.TemplateDTO;

@Component
public class SiteToDTOConverter implements Function<ISite, SiteDTO> {

    @Override
    public SiteDTO apply(final ISite entity) {
        final SiteDTO dto = new SiteDTO();
        dto.setId(entity.getId());
        dto.setSiteName(entity.getSiteName());
        dto.setBasepath(entity.getBasepath());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }



}

