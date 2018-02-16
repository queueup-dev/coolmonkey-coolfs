package com.sfl.coolmonkey.coolfs.service.storage.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.sfl.coolmonkey.coolfs.persistence.repositories.storage.StorageRepository;
import com.sfl.coolmonkey.coolfs.service.common.exception.ServicesRuntimeException;
import com.sfl.coolmonkey.coolfs.service.storage.StorageService;
import com.sfl.coolmonkey.coolfs.service.storage.component.StorageServiceConversionComponent;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileMetaDataDto;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileOrigin;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import com.sfl.coolmonkey.coolfs.service.test.AbstractServiceImplTest;
import org.bson.types.ObjectId;
import org.easymock.Capture;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;

import java.util.*;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 11:28 AM
 */
public class StorageServiceImplTest extends AbstractServiceImplTest {

    //region Test subject and mocks
    @TestSubject
    private StorageService storageService = new StorageServiceImpl();

    @Mock
    private StorageRepository storageRepository;

    @Mock
    private StorageServiceConversionComponent storageServiceConversionComponent;
    //endregion

    //region Constructors
    public StorageServiceImplTest() {
    }
    //endregion

    //region Test methods

    //region create
    @Test
    public void testCreateWithInvalidArguments() {
        // Test data
        FileStoreDto invalidDto;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        invalidDto = null;
        try {
            storageService.create(invalidDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        invalidDto = getHelper().createFileStoreDto();
        invalidDto.setInputStream(null);
        try {
            storageService.create(invalidDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        invalidDto = getHelper().createFileStoreDto();
        invalidDto.setFileName(null);
        try {
            storageService.create(invalidDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testCreateWhenGridFSFileIsNull() {
        // Test data
        final DBObject metaData = new BasicDBObject();
        metaData.put("uuid", UUID.randomUUID().toString());
        final FileStoreDto dto = getHelper().createFileStoreDto();
        // Reset
        resetAll();
        // Expectations
        expect(storageRepository.store(eq(dto.getInputStream()), eq(dto.getFileName()), eq(dto.getContentType()), isA(DBObject.class))).andReturn(null);
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageService.create(dto);
            fail("Exception should be thrown");
        } catch (final ServicesRuntimeException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testCreate() {
        // Test data
        final FileStoreDto dto = getHelper().createFileStoreDto();
        final String uuid = UUID.randomUUID().toString();
        final FileOrigin fileOrigin = FileOrigin.IMPORT_CSV;
        dto.setFileMetaDataDto(new FileMetaDataDto(uuid, fileOrigin));
        final GridFSDBFile gridFSDBFile = getHelper().createGridFSDBFile();
        gridFSDBFile.getMetaData().put("uuid", uuid);
        final FileStoreData fileStoreData = getHelper().createFileStoreData(uuid);
        final Capture<DBObject> capturedMetaData = new Capture<>();
        // Reset
        resetAll();
        // Expectations
        expect(storageRepository.store(eq(dto.getInputStream()), eq(dto.getFileName()), eq(dto.getContentType()), capture(capturedMetaData))).andReturn(gridFSDBFile);
        expect(storageServiceConversionComponent.assertIsInstanceOfStringAndGetCastedString(uuid)).andReturn(uuid);
        // Replay
        replayAll();
        // Run test scenario
        final String result = storageService.create(dto);
        // Verify
        verifyAll();
        assertNotNull(result);
        assertEquals(fileStoreData.getUuid(), result);
        assertEquals(uuid, capturedMetaData.getValue().get("uuid"));
        assertEquals(fileOrigin.toString(), capturedMetaData.getValue().get("fileOrigin"));
    }
    //endregion

    //region getById
    @Test
    public void testGetByIdWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageService.getById(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByIdWhenGridFSDBFileNotFound() {
        // Test data
        final ObjectId id = new ObjectId();
        // Reset
        resetAll();
        // Expectations
        expect(storageRepository.findById(id)).andReturn(null);
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageService.getById(id);
            fail("Exception should be thrown");
        } catch (final ServicesRuntimeException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetById() {
        // Test data
        final GridFSDBFile gridFSDBFile = getHelper().createGridFSDBFile();
        final ObjectId id = new ObjectId();
        final FileStoreData fileStoreData = getHelper().createFileStoreData();
        // Reset
        resetAll();
        // Expectations
        expect(storageRepository.findById(id)).andReturn(gridFSDBFile);
        expect(storageServiceConversionComponent.convertToFileStoreData(gridFSDBFile)).andReturn(fileStoreData);
        // Replay
        replayAll();
        // Run test scenario
        final FileStoreData result = storageService.getById(id);
        assertNotNull(result);
        assertEquals(fileStoreData, result);
        // Verify
        verifyAll();
    }
    //endregion

    //region getByMetaUuid
    @Test
    public void testGetByMetaUuidWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageService.getByMetaUuid(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByMetaUuidWhenGridFSDBFileNotFound() {
        // Test data
        final String uuid = UUID.randomUUID().toString();
        // Reset
        resetAll();
        // Expectations
        expect(storageRepository.findByMetaUuid(uuid)).andReturn(null);
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageService.getByMetaUuid(uuid);
            fail("Exception should be thrown");
        } catch (final ServicesRuntimeException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByMetaUuid() {
        // Test data
        final String uuid = UUID.randomUUID().toString();
        final GridFSDBFile gridFSDBFile = getHelper().createGridFSDBFile(uuid);
        final FileStoreData fileStoreData = getHelper().createFileStoreData(uuid);
        // Reset
        resetAll();
        // Expectations
        expect(storageRepository.findByMetaUuid(uuid)).andReturn(gridFSDBFile);
        expect(storageServiceConversionComponent.convertToFileStoreData(gridFSDBFile)).andReturn(fileStoreData);
        // Replay
        replayAll();
        // Run test scenario
        final FileStoreData result = storageService.getByMetaUuid(uuid);
        assertEquals(fileStoreData, result);
        // Verify
        verifyAll();
    }
    //endregion

    //region getByMetaUuids
    @Test
    public void testGetByMetaUuidsWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageService.getByMetaUuid(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        try {
            storageService.getByMetaUuids(Collections.singletonList(null));
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByMetaUuids() {
        // Test data
        final List<GridFSDBFile> gridFSDBFiles = Collections.singletonList(getHelper().createGridFSDBFile());
        final List<String> uuids = Collections.singletonList(UUID.randomUUID().toString());
        final FileStoreData fileStoreData = getHelper().createFileStoreData();
        final List<FileStoreData> fileStoreDataList = Collections.singletonList(fileStoreData);
        // Reset
        resetAll();
        // Expectations
        expect(storageRepository.findByMetaUuidIn(uuids)).andReturn(gridFSDBFiles);
        expect(storageServiceConversionComponent.convertToFileStoreData(isA(GridFSDBFile.class))).andReturn(fileStoreData).anyTimes();
        // Replay
        replayAll();
        // Run test scenario
        final List<FileStoreData> result = storageService.getByMetaUuids(uuids);
        assertNotNull(result);
        assertEquals(fileStoreDataList, result);
        // Verify
        verifyAll();
    }
    //endregion

    //region getByCompanyUuidAndFileNameAndCreatedAfterAndOrigin
    @Test
    public void testGetByCompanyUuidAndFileNameAndCreatedAfterWithInvalidArguments() {
        // Test data
        final String validString = "string";
        final Date validDate = new Date();
        final FileOrigin validFileOrigin = FileOrigin.IMPORT_CSV;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageService.getByCompanyUuidAndFileNameAndCreatedAfterAndOrigin(null, validString, validDate, validFileOrigin);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            storageService.getByCompanyUuidAndFileNameAndCreatedAfterAndOrigin(validString, null, validDate, validFileOrigin);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            storageService.getByCompanyUuidAndFileNameAndCreatedAfterAndOrigin(validString, validString, null, validFileOrigin);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            storageService.getByCompanyUuidAndFileNameAndCreatedAfterAndOrigin(validString, validString, validDate, null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByCompanyUuidAndFileNameAndCreatedAfter() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final String fileName = "fileName";
        final Date createdAfter = new Date();
        final List<GridFSDBFile> gridFSDBFiles = Arrays.asList(
                getHelper().createGridFSDBFile(),
                getHelper().createGridFSDBFile()
        );
        final List<FileStoreData> fileStoreDataList = Arrays.asList(
                getHelper().createFileStoreData(),
                getHelper().createFileStoreData()
        );
        final FileOrigin fileOrigin = FileOrigin.IMPORT_CSV;
        // Reset
        resetAll();
        // Expectations
        expect(storageRepository.findByMetaCompanyUuidAndFilenameAndUploadDateGreaterThanAndMetaFileOrigin(companyUuid, fileName, createdAfter, fileOrigin.toString())).andReturn(gridFSDBFiles);
        expect(storageServiceConversionComponent.convertToFileStoreData(gridFSDBFiles.get(0))).andReturn(fileStoreDataList.get(0));
        expect(storageServiceConversionComponent.convertToFileStoreData(gridFSDBFiles.get(1))).andReturn(fileStoreDataList.get(1));
        // Replay
        replayAll();
        // Run test scenario
        final List<FileStoreData> result = storageService.getByCompanyUuidAndFileNameAndCreatedAfterAndOrigin(companyUuid, fileName, createdAfter, fileOrigin);
        // Verify
        verifyAll();
        assertEquals(fileStoreDataList, result);
    }
    //endregion

    //region deleteByMetaUuid
    @Test
    public void testDeleteByMetaUuidWithInvalidArguments() {
        // test data
        // reset
        resetAll();
        // expectations
        // reply
        replayAll();
        // run test scenario
        try {
            storageService.getByMetaUuid(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
            // Expected
        }
        // verify
        verifyAll();
    }

    @Test
    public void testDeleteByMetaUuid() {
        // test data
        final String uuid = UUID.randomUUID().toString();
        // reset
        resetAll();
        // expectations
        storageRepository.deleteByMetaUuid(uuid);
        // reply
        replayAll();
        // run test scenario
        storageService.deleteByMetaUuid(uuid);
        // verify
        verifyAll();
    }
    //endregion

    //endregion

}