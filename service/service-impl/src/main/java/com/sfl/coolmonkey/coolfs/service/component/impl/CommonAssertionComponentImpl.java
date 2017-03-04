package com.sfl.coolmonkey.coolfs.service.component.impl;

import com.sfl.coolmonkey.coolfs.service.common.exception.ServicesRuntimeException;
import com.sfl.coolmonkey.coolfs.service.common.model.AbstractDomainEntityModel;
import com.sfl.coolmonkey.coolfs.service.component.CommonAssertionComponent;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 12/18/15
 * Time: 12:00 PM
 */
@Component
public class CommonAssertionComponentImpl implements CommonAssertionComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonAssertionComponentImpl.class);

    //region Constructors
    public CommonAssertionComponentImpl() {
        LOGGER.debug("Initializing common assertion component");
    }
    //endregion

    //region Public methods
    @Override
    public <T extends AbstractDomainEntityModel> void assertDomainModelNotNullForId(@Nonnull final ObjectId id, final T domainModel) {
        if (domainModel == null) {
            LOGGER.error("Can not find domain model for id - {}", id);
            throwServiceRuntimeException("Can not find domain model for id - " + id);
        }
    }

    @Override
    public <T extends AbstractDomainEntityModel> void assertDomainModelNotNullForUuid(@Nonnull final String uuid, final T domainModel) {
        if (domainModel == null) {
            LOGGER.debug("Can not find domain model for uuid - {}", uuid);
            throwServiceRuntimeException("Can not find domain model for uuid - " + uuid);
        }
    }
    //endregion

    //region Utility methods
    private void throwServiceRuntimeException(final String message) {
        throw new ServicesRuntimeException(message);
    }
    //endregion
}
