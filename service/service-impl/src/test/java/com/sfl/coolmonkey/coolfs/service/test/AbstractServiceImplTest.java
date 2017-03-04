package com.sfl.coolmonkey.coolfs.service.test;

import com.sfl.coolmonkey.coolfs.service.helper.ServiceImplTestHelper;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/14/15
 * Time: 3:12 PM
 */
@RunWith(EasyMockRunner.class)
@Ignore
public abstract class AbstractServiceImplTest extends EasyMockSupport {

    //region Properties
    private final ServiceImplTestHelper helper;
    //endregion

    //region Constructors
    public AbstractServiceImplTest() {
        helper = new ServiceImplTestHelper();
    }
    //endregion

    //region Properties getters and setters
    protected ServiceImplTestHelper getHelper() {
        return helper;
    }
    //endregion
}
