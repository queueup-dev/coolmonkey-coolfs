package com.sfl.coolmonkey.coolfs.facade.storage.component.impl;

import com.sfl.coolmonkey.coolfs.api.model.storage.FileUploadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import com.sfl.coolmonkey.coolfs.facade.storage.component.StorageFacadeConversionComponent;
import com.sfl.coolmonkey.coolfs.facade.test.AbstractFacadeImplTest;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileOrigin;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import ma.glasnost.orika.MapperFacade;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 7:40 PM
 */
public class StorageFacadeConversionComponentImplTest extends AbstractFacadeImplTest {

    //region Test subject and mocks
    @TestSubject
    private StorageFacadeConversionComponent storageFacadeConversionComponent = new StorageFacadeConversionComponentImpl();

    @Mock
    private MapperFacade mapperFacade;
    //endregion

    //region Constructors
    public StorageFacadeConversionComponentImplTest() {
    }
    //endregion

    //region Test methods

    //region buildFileStoreDtoFromUploadFileModel
    @Test
    public void testBuildFileStoreDtoFromUploadFileModelWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageFacadeConversionComponent.buildFileStoreDtoFromUploadFileModel(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testBuildFileStoreDtoFromUploadFileModel() {
        // Test data
        final FileUploadModel model = getHelper().createFileUploadModel();
        final FileOrigin fileOrigin = FileOrigin.CONTENT_PARTNER_LOGO;
        // Reset
        resetAll();
        // Expectations
        expect(mapperFacade.map(model.getFileOrigin(), FileOrigin.class)).andReturn(fileOrigin);
        // Replay
        replayAll();
        // Run test scenario
        final FileStoreDto dto = storageFacadeConversionComponent.buildFileStoreDtoFromUploadFileModel(model);
        // Verify
        verifyAll();
        assertNotNull(dto);
        assertEquals(model.getFileName(), dto.getFileName());
        assertEquals(model.getContentType(), dto.getContentType());
        assertEquals(fileOrigin, dto.getFileMetaDataDto().getFileOrigin());
    }
    //endregion

    //region buildStoredFileInfoModelFromFileStoreData
    @Test
    public void testBuildStoredFileInfoModelFromFileStoreDataWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageFacadeConversionComponent.buildStoredFileInfoModelFromFileStoreData(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testBuildStoredFileInfoModelFromFileStoreData() {
        // Test data
        final FileStoreData fileStoreData = getHelper().createFileStoreData();
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        final StoredFileInfoModel model = storageFacadeConversionComponent.buildStoredFileInfoModelFromFileStoreData(fileStoreData);
        assertStoredFileInfoModel(fileStoreData, model);
        // Verify
        verifyAll();
    }
    //endregion

    //region buildStoredFileInfoModelsFromFileStoreDataList
    @Test
    public void testBuildStoredFileInfoModelsFromFileStoreDataListWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageFacadeConversionComponent.buildStoredFileInfoModelsFromFileStoreDataList(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testBuildStoredFileInfoModelsFromFileStoreDataList() {
        // Test data
        final List<FileStoreData> fileStoreDataList = Collections.singletonList(getHelper().createFileStoreData());
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        final List<StoredFileInfoModel> models = storageFacadeConversionComponent.buildStoredFileInfoModelsFromFileStoreDataList(fileStoreDataList);
        assertStoredFileInfoModels(fileStoreDataList, models);
        // Verify
        verifyAll();
    }
    //endregion

    //endregion

    //region Utility methods
    private void assertStoredFileInfoModel(final FileStoreData fileStoreData, final StoredFileInfoModel model) {
        assertNotNull(fileStoreData);
        assertNotNull(model);
        assertEquals(fileStoreData.getFileName(), model.getFileName());
        assertEquals(fileStoreData.getContentType(), model.getContentType());
        assertEquals(fileStoreData.getCreated(), model.getCreated());
        assertEquals(fileStoreData.getUuid(), model.getUuid());
    }

    private void assertStoredFileInfoModels(final List<FileStoreData> fileStoreDataList, final List<StoredFileInfoModel> models) {
        assertNotNull(fileStoreDataList);
        assertNotNull(models);
        assertEquals(fileStoreDataList.size(), models.size());
        for (int i = 0; i < fileStoreDataList.size(); i++) {
            assertEquals(fileStoreDataList.get(i).getUuid(), models.get(i).getUuid());
            assertEquals(fileStoreDataList.get(i).getFileName(), models.get(i).getFileName());
            assertEquals(fileStoreDataList.get(i).getContentType(), models.get(i).getContentType());
            assertEquals(fileStoreDataList.get(i).getCreated(), models.get(i).getCreated());
        }
    }
    //endregion
}