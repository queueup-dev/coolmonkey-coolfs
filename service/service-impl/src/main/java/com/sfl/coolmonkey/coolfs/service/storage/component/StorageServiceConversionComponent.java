package com.sfl.coolmonkey.coolfs.service.storage.component;

import com.mongodb.gridfs.GridFSDBFile;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 8:51 PM
 */
public interface StorageServiceConversionComponent {
    /**
     * Convert to file store data file store data.
     *
     * @param gridFSFile the grid fs file
     * @return the file store data
     */
    @Nonnull
    FileStoreData convertToFileStoreData(@Nonnull final GridFSDBFile gridFSFile);

    /**
     * Assert obj is instance of string and get casted string.
     *
     * @param obj the uuid object
     * @return the string
     */
    @Nonnull
    String assertIsInstanceOfStringAndGetCastedString(final Object obj);
}
