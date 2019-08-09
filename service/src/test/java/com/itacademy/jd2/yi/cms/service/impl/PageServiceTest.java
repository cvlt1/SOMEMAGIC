package com.itacademy.jd2.yi.cms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.yi.cms.dao.api.entity.enums.PageStatus;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;

public class PageServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		IPage entity = saveNewPage();

		final IPage entityFromDb = pageService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getSite().getId(), entityFromDb.getSite().getId());
		assertEquals(entity.getTemplate().getId(), entityFromDb.getTemplate().getId());
		assertEquals(entity.getPath(), entityFromDb.getPath());
		assertEquals(entity.getPageStatus(), entityFromDb.getPageStatus());
		assertEquals(entity.getCreator().getId(), entityFromDb.getCreator().getId());
		assertEquals(entity.getPageTitle(), entityFromDb.getPageTitle());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testSearch() {
		/*
		 * List<IPage> result = pageService.search("test");
		 * assertTrue(result.isEmpty());
		 */
		List<IPage> result;
		IPage savedModel = savePage("searchable page1");
		List<IPage> foundModels = pageService.search("searchable page1");
		assertTrue(foundModels.size() == 1);
		assertTrue(foundModels.get(0).getId().equals(savedModel.getId()));

		IPage validPage = savePage("the best model in the world");
		result = pageService.search("world model best");
		assertEquals(1, result.size());
		assertEquals(validPage.getId(), result.get(0).getId());
	}

	private IPage savePage(String description) {
		final IPage entity = pageService.createEntity();
		entity.setSite(saveNewSite());
		entity.setTemplate(saveNewTemplate());
		entity.setPath("path-" + getRandomPrefix());
		entity.setPageStatus(PageStatus.EDITED);
		entity.setCreator(saveNewUserAccount());
		entity.setPageTitle(description);

		pageService.save(entity);
		return entity;

	}

}
