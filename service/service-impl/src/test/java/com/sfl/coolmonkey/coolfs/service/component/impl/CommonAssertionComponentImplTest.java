package com.sfl.coolmonkey.coolfs.service.component.impl;

import com.sfl.coolmonkey.coolfs.service.common.exception.ServicesRuntimeException;
import com.sfl.coolmonkey.coolfs.service.component.CommonAssertionComponent;
import com.sfl.coolmonkey.coolfs.service.test.AbstractServiceImplTest;
import org.bson.types.ObjectId;
import org.easymock.TestSubject;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.fail;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 12/18/15
 * Time: 12:03 PM
 */
public class CommonAssertionComponentImplTest extends AbstractServiceImplTest {

    //region Properties
    private ObjectId validId = new ObjectId();

    private String validUuid = UUID.randomUUID().toString();
    //endregion

    //region Test subject and mocks
    @TestSubject
    private CommonAssertionComponent commonAssertionComponent = new CommonAssertionComponentImpl();
    //endregion

    //region Constructors
    public CommonAssertionComponentImplTest() {
    }
    //endregion

    //region Test methods

    //region assertDomainModelNotNullForId
    @Test
    public void testAssertDomainModelNotNullForIdWithAssertionErrors() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            commonAssertionComponent.assertDomainModelNotNullForId(validId, null);
            fail("Exception should be thrown");
        } catch (final ServicesRuntimeException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testAssertDomainModelNotNullForId() {
        // Test data
//        final Email email = getHelper().createEmail();
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
//        commonAssertionComponent.assertDomainModelNotNullForId(validId, email);
        // Verify
        verifyAll();
    }
    //endregion

    //region assertDomainModelNotNullForUuid
    @Test
    public void testAssertDomainModelNotNullForUuidWithAssertionErrors() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            commonAssertionComponent.assertDomainModelNotNullForUuid(validUuid, null);
            fail("Exception should be thrown");
        } catch (final ServicesRuntimeException ex) {
            // Expected
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testAssertDomainModelNotNullForUuid() {
        // Test data
        // TODO
//        final Email email = getHelper().createEmail();
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
//        commonAssertionComponent.assertDomainModelNotNullForUuid(validUuid, email);
        // Verify
        verifyAll();
    }
    //endregion

    //endregion
}