package com.sfl.coolmonkey.coolfs.service.test;

import com.sfl.coolmonkey.coolfs.service.helper.ServiceIntegrationTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/3/15
 * Time: 3:49 PM
 */
@ContextConfiguration("classpath:applicationContext-service-integrationtest.xml")
public abstract class AbstractServiceIntegrationTest extends AbstractJUnit4SpringContextTests {

    //region Properties
    @Autowired
    private ServiceIntegrationTestHelper helper;
    //endregion

    //region Constructors
    public AbstractServiceIntegrationTest() {
    }
    //endregion

    //region Properties getters and setters
    public ServiceIntegrationTestHelper getHelper() {
        return helper;
    }
    //endregion
}
