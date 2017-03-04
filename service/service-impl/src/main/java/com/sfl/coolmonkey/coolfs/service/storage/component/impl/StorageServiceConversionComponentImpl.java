package com.sfl.coolmonkey.coolfs.service.storage.component.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.sfl.coolmonkey.coolfs.service.storage.component.StorageServiceConversionComponent;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 8:52 PM
 */
@Component
public class StorageServiceConversionComponentImpl implements StorageServiceConversionComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceConversionComponentImpl.class);

    //region Dependencies
    //endregion

    //region Constructors
    public StorageServiceConversionComponentImpl() {
        LOGGER.debug("Initializing storage service conversion component");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public FileStoreData convertToFileStoreData(@Nonnull final GridFSDBFile gridFSFile) {
        Assert.notNull(gridFSFile);
        return new FileStoreData(
                assertIsInstanceOfStringAndGetCastedString(gridFSFile.getMetaData().get("uuid")),
                gridFSFile.getFilename(),
                gridFSFile.getContentType(),
                gridFSFile.getUploadDate(),
                gridFSFile.getInputStream(),
                gridFSFile.getLength()
        );
    }

    @Nonnull
    @Override
    public String assertIsInstanceOfStringAndGetCastedString(final Object obj) {
        Assert.notNull(obj);
        if (!(obj instanceof String)) {
            LOGGER.error("The object - {} is not instance of string", obj);
            throw new IllegalArgumentException("The object - " + obj + " is not instance of string");
        }
        return (String) obj;
    }
    //endregion
}
