package com.sfl.coolmonkey.coolfs.service.storage;

import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileOrigin;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import org.bson.types.ObjectId;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 6:22 PM
 */
public interface StorageService {
    /**
     * Creates and stores storage in to GridFS
     * <p>Notice that this method will generate uuid if it's null in dto</p>
     *
     * @param dto the dto
     */
    String create(@Nonnull final FileStoreDto dto);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return FileStoreData by id
     */
    @Nonnull
    FileStoreData getById(@Nonnull final ObjectId id);

    /**
     * Gets by meta uuid.
     *
     * @param uuid the uuid
     * @return FileStoreData by meta uuid
     */
    @Nonnull
    FileStoreData getByMetaUuid(@Nonnull final String uuid);

    /**
     * Gets by meta uuid list.
     *
     * @param uuids the uuids
     * @return list of FileStoreData
     */
    @Nonnull
    List<FileStoreData> getByMetaUuids(@Nonnull final List<String> uuids);

    /**
     * Retrieves all files which match given criteria
     *
     * @param companyUuid  the company uuid
     * @param fileName     the file name
     * @param createdAfter the created after
     * @param origin       the origin
     * @return by company uuid and file name and created after and origin
     */
    @Nonnull
    List<FileStoreData> getByCompanyUuidAndFileNameAndCreatedAfterAndOrigin(@Nonnull final String companyUuid,
                                                                            @Nonnull final String fileName,
                                                                            @Nonnull final Date createdAfter,
                                                                            @Nonnull final FileOrigin origin);

    /**
     * Deletes by meta uuid.
     *
     * @param uuid the uuid
     */
    void deleteByMetaUuid(@Nonnull final String uuid);
}
