package com.itacademy.jd2.yi.cms.service.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;

public class CssItemServiceTest extends AbstractTest {
	
	 @Test
	    public void createTest() {
	        final ICssItem entity = cssItemService.createEntity();
	        entity.setContent("content-" + getRandomPrefix());
	        entity.setSite(saveNewSite());
	        cssItemService.save(entity);

	        final ICssItem entityFromDb = cssItemService.get(entity.getId());

	        assertEquals(entity.getContent(), entityFromDb.getContent());
	        assertEquals(entity.getSite().getId(), entityFromDb.getSite().getId());
	        assertNotNull(entity.getContent(), entityFromDb.getContent());
	        assertNotNull(entityFromDb.getId());
	        assertNotNull(entityFromDb.getCreated());
	        assertNotNull(entityFromDb.getUpdated());
	        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}
	 
	 @Test
	    public void testUpdate() throws InterruptedException {
	        final ICssItem entity = cssItemService.createEntity();
	        entity.setSite(saveNewSite());
	        
	        String newContent = entity.getContent() + "_updated";
	        entity.setContent(newContent);
	        Thread.sleep(2000);
	        cssItemService.save(entity);
	        


	        final ICssItem entityFromDb = cssItemService.get(entity.getId());

	        assertNotNull(entityFromDb);
	        assertEquals(entity.getContent(), entityFromDb.getContent());
	        assertEquals(entity.getSite().getId(), entityFromDb.getSite().getId());
	        assertNotNull(entityFromDb.getId());
	        assertNotNull(entityFromDb.getCreated());
	        assertNotNull(entityFromDb.getUpdated());
	        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	        assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	 }

	   @Test
	    public void testGetAll() {
	        final int intialCount = cssItemService.getAll().size();

	        final int randomObjectsCount = getRandomObjectsCount();
	        for (int i = 0; i < randomObjectsCount; i++) {
	            saveNewSite();
	        }

	        final List<ICssItem> allEntities = cssItemService.getAll();

	        for (final ICssItem entityFromDb : allEntities) {
	        	assertNotNull(entityFromDb.getId());
	        	assertNotNull(entityFromDb.getSite().getId());
	        	assertNotNull(entityFromDb.getContent());
	            assertNotNull(entityFromDb.getCreated());
	            assertNotNull(entityFromDb.getUpdated());
	            
	        }

	        assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}
	   
	    @Test
	    public void testDelete() {
	        final ICssItem entity = cssItemService.createEntity();
	        cssItemService.delete(entity.getId());
	        cssItemService.delete(entity.getSite().getId());
	        assertNull(cssItemService.get(entity.getId()));
	}
	    
	    @Test
	    public void testDeleteAll() {
	    	cssItemService.createEntity();
	    	cssItemService.deleteAll();
	        assertEquals(0, cssItemService.getAll().size());
	    }
}
	 
	 
