package com.sfl.coolmonkey.coolfs.persistence.test;

import com.sfl.coolmonkey.coolfs.service.helper.ServiceIntegrationTestHelper;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/5/16
 * Time: 11:29 AM
 */
@Ignore
@ContextConfiguration("classpath:applicationContext-persistence-integrationtest.xml")
public abstract class AbstractPersistenceIntegrationTest extends AbstractJUnit4SpringContextTests {

    //region Dependencies
    @Autowired
    private ServiceIntegrationTestHelper helper;
    //endregion

    //region Constructors
    public AbstractPersistenceIntegrationTest() {
    }
    //endregion

    //region Public methods
    //endregion

    //region Properties getters and setters
    public ServiceIntegrationTestHelper getHelper() {
        return helper;
    }
    //endregion
}
