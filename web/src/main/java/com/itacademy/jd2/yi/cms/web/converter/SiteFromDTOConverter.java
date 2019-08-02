package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.service.ISiteService;
import com.itacademy.jd2.yi.cms.service.ITemplateService;
import com.itacademy.jd2.yi.cms.web.dto.SiteDTO;
import com.itacademy.jd2.yi.cms.web.dto.TemplateDTO;

@Component
public class SiteFromDTOConverter implements Function<SiteDTO, ISite> {

    @Autowired
    private ISiteService siteService;

    @Override
    public ISite apply(final SiteDTO dto) {
        final ISite entity = siteService.createEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getSiteName());
        entity.setBasepath(dto.getBasepath());
        return entity;
    }
}
