package com.sfl.coolmonkey.coolfs.persistence.repositories.storage;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sfl.coolmonkey.coolfs.persistence.test.AbstractPersistenceIntegrationTest;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 8:19 PM
 */
@Component
public class StorageRepositoryIntegrationTest extends AbstractPersistenceIntegrationTest {

    //region Dependencies
    @Autowired
    private StorageRepository storageRepository;
    //endregion

    //region Constructors
    public StorageRepositoryIntegrationTest() {
    }
    //endregion

    //region Test methods
    @Test
    public void testStore() {
        // prepare data
        final DBObject meta = new BasicDBObject();
        meta.put("uuid", UUID.randomUUID().toString());
        // run test scenario
        storageRepository.store(IOUtils.toInputStream("My test stream data"), "test.jpg", "image/jpg", meta);
    }

    @Test
    public void testFindById() {
        // prepare data
        // run test scenario
        storageRepository.findById(new ObjectId());
    }

    @Test
    public void testFindByUuid() {
        // prepare data
        // run test scenario
        storageRepository.findByMetaUuid(UUID.randomUUID().toString());
    }

    @Test
    public void testFindByMetaUuidIn() {
        // prepare data
        // run test scenario
        storageRepository.findByMetaUuidIn(Collections.singletonList(UUID.randomUUID().toString()));
    }

    @Test
    public void testFindByMetaCompanyUuidAndFilenameAndUploadDateGreaterThanAndMetaFileOrigin() {
        // prepare data
        // run test scenario
        storageRepository.findByMetaCompanyUuidAndFilenameAndUploadDateGreaterThanAndMetaFileOrigin(
                UUID.randomUUID().toString(),
                "test file name",
                new Date(), "test file origin"
        );
    }

    @Test
    public void testDeleteByMetaUuid() {
        // prepare data
        // run test scenario
        storageRepository.deleteByMetaUuid("ecae0968-a1c0-4ea0-9a8f-1409150c72b1");
    }
    //endregion

}
