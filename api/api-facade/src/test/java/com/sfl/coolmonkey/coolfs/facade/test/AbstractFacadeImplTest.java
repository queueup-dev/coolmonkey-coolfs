package com.sfl.coolmonkey.coolfs.facade.test;

import com.sfl.coolmonkey.coolfs.facade.helper.FacadeImplTestHelper;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/20/15
 * Time: 2:50 PM
 */
@RunWith(EasyMockRunner.class)
@Ignore
public abstract class AbstractFacadeImplTest extends EasyMockSupport {
    //region Properties
    private final FacadeImplTestHelper helper;
    //endregion

    //region Constructors
    public AbstractFacadeImplTest() {
        helper = new FacadeImplTestHelper();
    }
    //endregion

    //region Properties getters and setters
    public FacadeImplTestHelper getHelper() {
        return helper;
    }
    //endregion
}
