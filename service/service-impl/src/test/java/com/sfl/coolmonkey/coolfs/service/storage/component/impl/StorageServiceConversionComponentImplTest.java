package com.sfl.coolmonkey.coolfs.service.storage.component.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.sfl.coolmonkey.coolfs.service.storage.component.StorageServiceConversionComponent;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import com.sfl.coolmonkey.coolfs.service.test.AbstractServiceImplTest;
import org.easymock.TestSubject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 12:40 PM
 */
public class StorageServiceConversionComponentImplTest extends AbstractServiceImplTest {

    //region Test subject and mocks
    @TestSubject
    private StorageServiceConversionComponent storageServiceConversionComponent = new StorageServiceConversionComponentImpl();
    //endregion

    //region Constructors
    public StorageServiceConversionComponentImplTest() {
    }
    //endregion

    //region Test methods

    //region convertToFileStoreData
    @Test
    public void testConvertToFileStoreDataWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageServiceConversionComponent.convertToFileStoreData(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testConvertToFileStoreData() {
        // Test data
        final GridFSDBFile fsFile = getHelper().createGridFSDBFile();
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        final FileStoreData result = storageServiceConversionComponent.convertToFileStoreData(fsFile);
        assertNotNull(result);
        assertEquals(fsFile.getMetaData().get("uuid"), result.getUuid());
        // Verify
        verifyAll();
    }
    //endregion

    //region assertIsInstanceOfStringAndGetCastedString
    @Test
    public void testAssertIsInstanceOfStringAndGetCastedStringWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageServiceConversionComponent.assertIsInstanceOfStringAndGetCastedString(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testAssertIsInstanceOfStringAndGetCastedStringWhenObjectIsNotInstanceOfString() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            storageServiceConversionComponent.assertIsInstanceOfStringAndGetCastedString(new Object());
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testAssertIsInstanceOfStringAndGetCastedStringWhenTheObjetcIsInstanceOfString() {
        // Test data
        final String expectedString = "test expected string";
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        final String result = storageServiceConversionComponent.assertIsInstanceOfStringAndGetCastedString(expectedString);
        assertNotNull(result);
        assertEquals(expectedString, result);
        // Verify
        verifyAll();
    }
    //endregion

    //endregion

}