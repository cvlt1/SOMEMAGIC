package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.web.dto.PageDTO;
import com.itacademy.jd2.yi.cms.web.dto.TemplateDTO;

@Component
public class PageToDTOConverter implements Function<IPage, PageDTO> {

    @Override
    public PageDTO apply(final IPage entity) {
        final PageDTO dto = new PageDTO();
        dto.setId(entity.getId());
        dto.setPath(entity.getPath());
        dto.setTitle(entity.getTitle());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }

}

