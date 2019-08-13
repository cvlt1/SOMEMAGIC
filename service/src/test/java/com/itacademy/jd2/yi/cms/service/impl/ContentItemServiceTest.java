package com.itacademy.jd2.yi.cms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IContentItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;

public class ContentItemServiceTest extends AbstractTest {

	
	@Test
    public void createTest() {
        final IContentItem entity = contentItemService.createEntity();
        entity.setHtml("html-" + getRandomPrefix());
        entity.setSiteId(saveNewSite());
        entity.setTitle("title-" + getRandomPrefix());
        contentItemService.save(entity);

        final IContentItem entityFromDb = contentItemService.getFullInfo(entity.getId());

        assertEquals(entity.getHtml(), entityFromDb.getHtml());
        assertEquals(entity.getTitle(), entityFromDb.getTitle());
        assertEquals(entity.getSiteId().getId(), entityFromDb.getSiteId().getId());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }
}
