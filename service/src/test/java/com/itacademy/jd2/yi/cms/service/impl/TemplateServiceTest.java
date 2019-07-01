package com.itacademy.jd2.yi.cms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.ITemplate;

public class TemplateServiceTest extends AbstractTest {

    @Test
    public void testCreate() {
        final ITemplate entity = saveNewTemplate();

        final ITemplate entityFromDb = templateService.get(entity.getId());

        assertNotNull(entityFromDb);

        assertNotNull(entityFromDb.getId());

        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }
    
    @Test
    public void testCreateMultiple() {
        int initialSize = templateService.getAll().size();

        final ITemplate entity1 = templateService.createEntity();
        entity1.setJspPath("D:\\templates\\-" + getRandomPrefix() + ".jsp");

        try {
            final ITemplate entity2 = templateService.createEntity();
            templateService.save(entity1, entity2);
            fail("Template  save should fail if name not specified");
        } catch (Exception e) {
            assertEquals(initialSize, templateService.getAll().size());
        }

}
    
    @Test
    public void testUpdate() throws InterruptedException {
        final ITemplate entity = saveNewTemplate();

        String newJspPath = entity.getJspPath() + "_updated";
        entity.setJspPath(newJspPath);
        Thread.sleep(2000);
        templateService.save(entity);

        final ITemplate entityFromDb = templateService.get(entity.getId());

        assertNotNull(entityFromDb);
        assertEquals(entity.getJspPath(), entityFromDb.getJspPath());

        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        //assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
        assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
}
    @Test
    public void testGetAll() {
        final int intialCount = templateService.getAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewTemplate();
        }

        final List<ITemplate> allEntities = templateService.getAll();

        for (final ITemplate entityFromDb : allEntities) {
        	assertNotNull(entityFromDb.getId());
            assertNotNull(entityFromDb.getJspPath());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }

        assertEquals(randomObjectsCount + intialCount, allEntities.size());
}
    
    @Test
    public void testDelete() {
        final ITemplate entity = saveNewTemplate();
        templateService.delete(entity.getId());
        assertNull(templateService.get(entity.getId()));
}
    
    @Test
    public void testDeleteAll() {
        saveNewTemplate();
        templateService.deleteAll();
        assertEquals(0, templateService.getAll().size());
    }
}
    
    
