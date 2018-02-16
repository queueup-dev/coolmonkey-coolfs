package com.sfl.coolmonkey.coolfs.persistence.repositories.storage.impl;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.sfl.coolmonkey.coolfs.persistence.repositories.storage.StorageRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 8:39 PM
 */
@Component
public class StorageRepositoryImpl implements StorageRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageRepositoryImpl.class);

    //region Constants
    private static final String METADATA_UUID = "metadata.uuid";
    //endregion

    //region Dependencies
    @Autowired
    private GridFsTemplate gridFsTemplate;
    //endregion

    //region Constructors
    public StorageRepositoryImpl() {
        LOGGER.debug("Initializing storage repository");
    }
    //endregion

    //region Public methods
    @Override
    public GridFSFile store(@Nonnull final InputStream inputStream,
                            @Nonnull final String fileName,
                            @Nonnull final String contentType,
                            @Nonnull final DBObject metaData) {
        return gridFsTemplate.store(inputStream, fileName, contentType, metaData);
    }

    @Override
    public GridFSDBFile findById(@Nonnull final ObjectId id) {
        return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }

    @Override
    public GridFSDBFile findByMetaUuid(@Nonnull final String metaUuid) {
        return gridFsTemplate.findOne(new Query(Criteria.where(METADATA_UUID).is(metaUuid)));
    }

    @Override
    public List<GridFSDBFile> findByMetaUuidIn(@Nonnull final List<String> uuids) {
        return gridFsTemplate.find(new Query(Criteria.where(METADATA_UUID).in(uuids)));
    }

    @Override
    public void deleteByMetaUuid(@Nonnull final String metaUuid) {
        gridFsTemplate.delete(new Query(Criteria.where(METADATA_UUID).is(metaUuid)));
    }
    //endregion
}
