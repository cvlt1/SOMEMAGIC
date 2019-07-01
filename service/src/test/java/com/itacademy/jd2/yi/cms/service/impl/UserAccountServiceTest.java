package com.itacademy.jd2.yi.cms.service.impl;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

public class UserAccountServiceTest extends AbstractTest {

    @Test
    public void testCreate() {
        final IUserAccount entity = saveNewUserAccount();

        final IUserAccount entityFromDb = userAccountService.get(entity.getId());

        assertNotNull(entityFromDb);
        assertEquals(entity.getName(), entityFromDb.getName());
        assertEquals(entity.getEmail(), entityFromDb.getEmail());
        assertEquals(entity.getPassword(), entityFromDb.getPassword());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getEmail());
        assertNotNull(entityFromDb.getPassword());
        assertNotNull(entityFromDb.getRole());
        assertNotNull(entityFromDb.getStatus());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }
    
    @Test
    public void testCreateMultiple() {
        int initialSize = userAccountService.getAll().size();

        final IUserAccount entity1 = userAccountService.createEntity();
        entity1.setName("user_account-" + getRandomPrefix());

        try {
            final IUserAccount entity2 = userAccountService.createEntity();
            userAccountService.save(entity1, entity2);
            fail("User account save should fail if name not specified");
        } catch (Exception e) {
            assertEquals(initialSize, userAccountService.getAll().size());
        }

}
    
    @Test
    public void testUpdate() throws InterruptedException {
        final IUserAccount entity = saveNewUserAccount();

        String newName = entity.getName() + "_updated";
        entity.setName(newName);
        Thread.sleep(2000);
        userAccountService.save(entity);

        final IUserAccount entityFromDb = userAccountService.get(entity.getId());

        assertNotNull(entityFromDb);
        assertEquals(entity.getName(), entityFromDb.getName());
        assertEquals(entity.getEmail(), entityFromDb.getEmail());
        assertEquals(entity.getPassword(), entityFromDb.getPassword());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getEmail());
        assertNotNull(entityFromDb.getPassword());
        assertNotNull(entityFromDb.getRole());
        assertNotNull(entityFromDb.getStatus());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        //assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
        assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
}
    @Test
    public void testGetAll() {
        final int intialCount = userAccountService.getAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewUserAccount();
        }

        final List<IUserAccount> allEntities = userAccountService.getAll();

        for (final IUserAccount entityFromDb : allEntities) {
        	assertNotNull(entityFromDb.getId());
            assertNotNull(entityFromDb.getEmail());
            assertNotNull(entityFromDb.getPassword());
            assertNotNull(entityFromDb.getRole());
            assertNotNull(entityFromDb.getStatus());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }

        assertEquals(randomObjectsCount + intialCount, allEntities.size());
}
    
    @Test
    public void testDelete() {
        final IUserAccount entity = saveNewUserAccount();
        userAccountService.delete(entity.getId());
        assertNull(userAccountService.get(entity.getId()));
}
    
    @Test
    public void testDeleteAll() {
        saveNewUserAccount();
        userAccountService.deleteAll();
        assertEquals(0, userAccountService.getAll().size());
    }
}
    
    
