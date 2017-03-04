package com.sfl.coolmonkey.coolfs.facade.storage.component.impl;

import com.sfl.coolmonkey.commons.api.model.CommonErrorType;
import com.sfl.coolmonkey.coolfs.facade.storage.component.StorageFacadeValidationComponent;
import com.sfl.coolmonkey.coolfs.facade.test.AbstractFacadeImplTest;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import org.easymock.TestSubject;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 11/04/16
 * Time: 12:25
 */
public class StorageFacadeValidationComponentImplTest extends AbstractFacadeImplTest {

    //region Test subject and mocks
    @TestSubject
    private StorageFacadeValidationComponent storageFacadeValidationComponent = new StorageFacadeValidationComponentImpl();
    //endregion

    //region Constructors
    public StorageFacadeValidationComponentImplTest() {
    }
    //endregion

    //region Test methods

    //region validateFileMaxLength
    @Test
    public void testCheckIfFileLengthIsValidWithInvalidArguments() {
        // test data
        // reset
        resetAll();
        // expectations
        // reply
        replayAll();
        // run test scenario
        try {
            storageFacadeValidationComponent.validateFileMaxLength(null, 7L);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
            // Expected
        }
        // verify
        verifyAll();
    }

    @Test
    public void testValidateFileMaxLengthWhenFileMaxLengthIsNull() {
        // test data
        final FileStoreData fileStoreData = getHelper().createFileStoreData();
        // reset
        resetAll();
        // expectations
        // reply
        replayAll();
        // run test scenario
        final Map<CommonErrorType, Object> result = storageFacadeValidationComponent.validateFileMaxLength(fileStoreData, null);
        assertTrue(result.isEmpty());
        // verify
        verifyAll();
    }

    @Test
    public void testValidateFileLengthWhenFileMaxLengthIsGreatherThanFileMaxLength() {
        // test data
        final Long fileMaxLength = (long) (2 * 1024);
        final FileStoreData fileStoreData = getHelper().createFileStoreData();
        fileStoreData.setLength(fileMaxLength * 3);
        // reset
        resetAll();
        // expectations
        // reply
        replayAll();
        // run test scenario
        final Map<CommonErrorType, Object> result = storageFacadeValidationComponent.validateFileMaxLength(fileStoreData, fileMaxLength);
        assertNotNull(result);
        assertTrue(result.containsKey(CommonErrorType.IMPORT_FILE_MAX_SIZE_EXCEEDED));
        // verify
        verifyAll();
    }

    @Test
    public void testValidateFileMaxLength() {
        // test data
        final Long fileMaxLength = (long) (2 * 1024);
        final FileStoreData fileStoreData = getHelper().createFileStoreData();
        fileStoreData.setLength(1024L);
        // reset
        resetAll();
        // expectations
        // reply
        replayAll();
        // run test scenario
        final Map<CommonErrorType, Object> result = storageFacadeValidationComponent.validateFileMaxLength(fileStoreData, fileMaxLength);
        assertTrue(result.isEmpty());
        // verify
        verifyAll();
    }
    //endregion

    //endregion

}