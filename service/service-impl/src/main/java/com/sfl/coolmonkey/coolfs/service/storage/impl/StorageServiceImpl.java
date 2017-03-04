package com.sfl.coolmonkey.coolfs.service.storage.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.sfl.coolmonkey.coolfs.persistence.repositories.storage.StorageRepository;
import com.sfl.coolmonkey.coolfs.service.common.exception.ServicesRuntimeException;
import com.sfl.coolmonkey.coolfs.service.storage.StorageService;
import com.sfl.coolmonkey.coolfs.service.storage.component.StorageServiceConversionComponent;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileOrigin;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 6:26 PM
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    //region Dependencies
    @Autowired
    private StorageServiceConversionComponent storageServiceConversionComponent;

    @Autowired
    private StorageRepository storageRepository;
    //endregion

    //region Constructors
    public StorageServiceImpl() {
        LOGGER.debug("Initializing storage service");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public String create(@Nonnull final FileStoreDto dto) {
        assertFileStoreDto(dto);
        final DBObject metaData = new BasicDBObject();
        // In future add more meta data from dto.getFileMetaDataDto() if needed
        metaData.put("uuid", generateUuidIfNull(dto));
        metaData.put("companyUuid", dto.getFileMetaDataDto().getCompanyUuid());
        // At this point mongodb does not serialize java enums, see https://jira.mongodb.org/browse/JAVA-268
        // That's why we have to serialize file origin enum as string here
        metaData.put("fileOrigin", dto.getFileMetaDataDto().getFileOrigin().toString());
        final GridFSFile store = storageRepository.store(dto.getInputStream(), dto.getFileName(), dto.getContentType(), metaData);
        assertGridFSFileNotNullForDto(store, dto);
        return storageServiceConversionComponent.assertIsInstanceOfStringAndGetCastedString(store.getMetaData().get("uuid"));
    }

    @Nonnull
    @Override
    public FileStoreData getById(@Nonnull final ObjectId id) {
        Assert.notNull(id);
        final GridFSDBFile gridFSDBFile = storageRepository.findById(id);
        assertGridFSDBFileNotNullForId(gridFSDBFile, id);
        return storageServiceConversionComponent.convertToFileStoreData(gridFSDBFile);
    }

    @Nonnull
    @Override
    public FileStoreData getByMetaUuid(@Nonnull final String uuid) {
        Assert.notNull(uuid);
        final GridFSDBFile gridFSDBFile = storageRepository.findByMetaUuid(uuid);
        assertGridFSDBFileNotNullForUuid(gridFSDBFile, uuid);
        return storageServiceConversionComponent.convertToFileStoreData(gridFSDBFile);
    }

    @Nonnull
    @Override
    public List<FileStoreData> getByMetaUuids(@Nonnull final List<String> uuids) {
        Assert.notNull(uuids);
        Assert.noNullElements(uuids.toArray());
        final List<GridFSDBFile> gridFSDBFiles = storageRepository.findByMetaUuidIn(uuids);
        return buildFileStoreDataListFromGridFSDBFiles(gridFSDBFiles);
    }

    @Nonnull
    @Override
    public List<FileStoreData> getByCompanyUuidAndFileNameAndCreatedAfterAndOrigin(@Nonnull final String companyUuid,
                                                                                   @Nonnull final String fileName,
                                                                                   @Nonnull final Date createdAfter,
                                                                                   @Nonnull final FileOrigin origin) {
        Assert.notNull(companyUuid);
        Assert.notNull(fileName);
        Assert.notNull(createdAfter);
        Assert.notNull(origin);
        final List<GridFSDBFile> gridFSDBFiles = storageRepository.findByMetaCompanyUuidAndFilenameAndUploadDateGreaterThanAndMetaFileOrigin(
                companyUuid,
                fileName,
                createdAfter,
                origin.toString());
        return buildFileStoreDataListFromGridFSDBFiles(gridFSDBFiles);
    }

    @Override
    public void deleteByMetaUuid(@Nonnull final String uuid) {
        Assert.notNull(uuid, "The meta uuid should not be null");
        storageRepository.deleteByMetaUuid(uuid);
    }
    //endregion

    //region Utility method
    private String generateUuidIfNull(final FileStoreDto dto) {
        return dto.getFileMetaDataDto().getUuid() == null ? UUID.randomUUID().toString() : dto.getFileMetaDataDto().getUuid();
    }

    private void assertFileStoreDto(final FileStoreDto dto) {
        Assert.notNull(dto);
        Assert.notNull(dto.getInputStream());
        Assert.notNull(dto.getFileName());
    }

    private void assertGridFSFileNotNullForDto(final GridFSFile store, final FileStoreDto dto) {
        if (store == null) {
            LOGGER.error("Can not store file for dto - {}", dto);
            throw new ServicesRuntimeException("Can not store file for dto - " + dto);
        }
    }

    private void assertGridFSDBFileNotNullForId(final GridFSDBFile gridFSDBFile, final ObjectId id) {
        if (gridFSDBFile == null) {
            LOGGER.debug("Can not find grid fs db file for id - {}", id);
            throw new ServicesRuntimeException("Can not find grid fs db file for id - " + id);
        }
    }

    private void assertGridFSDBFileNotNullForUuid(final GridFSDBFile gridFSDBFile, final String uuid) {
        if (gridFSDBFile == null) {
            LOGGER.error("Can not find grid fs db file for uuid - {}", uuid);
            throw new ServicesRuntimeException("Can not find grid fs db file for uuid - " + uuid);
        }
    }

    private List<FileStoreData> buildFileStoreDataListFromGridFSDBFiles(final List<GridFSDBFile> gridFSDBFiles) {
        final List<FileStoreData> fileStoreDataList = new ArrayList<>();
        gridFSDBFiles.forEach(gridFSDBFile -> fileStoreDataList.add(storageServiceConversionComponent.convertToFileStoreData(gridFSDBFile)));
        return fileStoreDataList;
    }
    //endregion
}
