package com.itacademy.jd2.yi.cms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ICssItem;
import com.itacademy.jd2.yi.cms.dao.api.entity.table.ISite;

public class SiteServiceTest extends AbstractTest {

    @Test
    public void testCreate() {
        final ISite entity = saveNewSite();

        final ISite entityFromDb = siteService.get(entity.getId());

        assertNotNull(entityFromDb);

        assertNotNull(entityFromDb.getId());

        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }
    
    @Test
    public void testCreateMultiple() {
        int initialSize = siteService.getAll().size();

        final ISite entity1 = siteService.createEntity();
        entity1.setSiteName("sitename" + getRandomPrefix() + "asd");

        try {
            final ISite entity2 = siteService.createEntity();
            siteService.save(entity1, entity2);
            fail("Template  save should fail if name not specified");
        } catch (Exception e) {
            assertEquals(initialSize, templateService.getAll().size());
        }

}
    
    @Test
    public void testUpdate() throws InterruptedException {
        final ISite entity = saveNewSite();

        String newSiteName = entity.getSiteName() + "_updated";
        entity.setSiteName(newSiteName);
        Thread.sleep(2000);
        siteService.save(entity);

        final ISite entityFromDb = siteService.get(entity.getId());

        assertNotNull(entityFromDb);
        assertEquals(entity.getSiteName(), entityFromDb.getSiteName());
        assertEquals(entity.getBasepath(), entityFromDb.getBasepath());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        //assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
        assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
}
    @Test
    public void testGetAll() {
        final int intialCount = siteService.getAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewSite();
        }

        final List<ISite> allEntities = siteService.getAll();

        for (final ISite entityFromDb : allEntities) {
        	assertNotNull(entityFromDb.getId());
            assertNotNull(entityFromDb.getSiteName());
            assertNotNull(entityFromDb.getBasepath());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }

        assertEquals(randomObjectsCount + intialCount, allEntities.size());
}
    
    @Test
    public void testDelete() {
        final ISite entity = saveNewSite();
        siteService.delete(entity.getId());
        assertNull(siteService.get(entity.getId()));
}
    
    @Test
    public void testDeleteAll() {
    	//saveNewCssItem();
        saveNewSite();
        siteService.deleteAll();
        cssItemService.deleteAll();
        assertEquals(0, siteService.getAll().size());
    }
}
