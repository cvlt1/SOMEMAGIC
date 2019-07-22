package com.itacademy.jd2.yi.cms.service.impl;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IPage;

public class PageServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		IPage entity = saveNewPage();

		final IPage entityFromDb = pageService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getSite().getId(), entityFromDb.getSite().getId());
		assertEquals(entity.getParentId(), entityFromDb.getParentId());
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

}
