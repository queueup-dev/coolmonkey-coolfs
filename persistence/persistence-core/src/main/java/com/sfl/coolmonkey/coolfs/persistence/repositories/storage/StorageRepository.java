package com.sfl.coolmonkey.coolfs.persistence.repositories.storage;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.bson.types.ObjectId;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 6:26 PM
 */
public interface StorageRepository {
    /**
     * Store grid fs file.
     *
     * @param inputStream the input stream
     * @param fileName    the file name
     * @param contentType the content type
     * @param metaData    the meta data
     * @return the grid fs file
     */
    GridFSFile store(@Nonnull final InputStream inputStream, @Nonnull final String fileName, @Nonnull final String contentType, @Nonnull final DBObject metaData);

    /**
     * Find by id grid fsdb file.
     *
     * @param id the id
     * @return the grid fsdb file
     */
    GridFSDBFile findById(@Nonnull final ObjectId id);

    /**
     * Find by meta uuid grid fsdb file.
     *
     * @param metaUuid the meta uuid
     * @return grid fsdb file
     */
    GridFSDBFile findByMetaUuid(@Nonnull final String metaUuid);

    /**
     * Find by meta uuid in list.
     *
     * @param uuids the uuids
     * @return the list of GridFSDBFile
     */
    List<GridFSDBFile> findByMetaUuidIn(@Nonnull final List<String> uuids);

    /**
     * Delete by meta uuid.
     *
     * @param metaUuid the meta uuid
     */
    void deleteByMetaUuid(@Nonnull final String metaUuid);
}
