package com.sfl.coolmonkey.coolfs.facade.storage.component;

import com.sfl.coolmonkey.commons.api.model.CommonErrorType;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 11/04/16
 * Time: 12:18
 */
public interface StorageFacadeValidationComponent {

    /**
     * Validate file max length.
     *
     * @param fileStoreData the file store data
     * @param fileMaxLength the max file length
     * @return the result response model
     */
    @Nonnull
    Map<CommonErrorType, Object> validateFileMaxLength(@Nonnull final FileStoreData fileStoreData, @Nullable final Long fileMaxLength);
}
