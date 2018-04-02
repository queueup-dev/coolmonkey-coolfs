package com.sfl.coolmonkey.coolfs.facade.storage.component.impl;

import com.sfl.coolmonkey.coolfs.api.model.common.CommonErrorType;
import com.sfl.coolmonkey.coolfs.facade.storage.component.StorageFacadeValidationComponent;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 11/04/16
 * Time: 12:22
 */
@Component
public class StorageFacadeValidationComponentImpl implements StorageFacadeValidationComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageFacadeValidationComponentImpl.class);

    //region Dependencies
    //endregion

    //region Constructors
    public StorageFacadeValidationComponentImpl() {
        LOGGER.debug("Initializing");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public Map<CommonErrorType, Object> validateFileMaxLength(@Nonnull final FileStoreData fileStoreData, @Nullable final Long fileMaxLength) {
        Assert.notNull(fileStoreData, "The file store data should not be null");
        if (fileMaxLength != null && fileStoreData.getLength() > fileMaxLength) {
            final Map<CommonErrorType, Object> errors = new EnumMap<>(CommonErrorType.class);
            errors.put(CommonErrorType.FILE_MAX_SIZE_EXCEEDED, null);
            return errors;
        }
        return Collections.emptyMap();
    }
    //endregion
}
