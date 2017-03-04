package com.sfl.coolmonkey.coolfs.facade.storage.component.impl;

import com.sfl.coolmonkey.coolfs.api.model.storage.FileUploadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import com.sfl.coolmonkey.coolfs.facade.storage.component.StorageFacadeConversionComponent;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileMetaDataDto;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileOrigin;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 7:37 PM
 */
@Component
public class StorageFacadeConversionComponentImpl implements StorageFacadeConversionComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageFacadeConversionComponentImpl.class);

    //region Dependencies
    @Autowired
    private MapperFacade mapperFacade;
    //endregion

    //region Constructors
    public StorageFacadeConversionComponentImpl() {
        LOGGER.debug("Initializing storage facade conversion component");
    }
    //endregion

    //region Public methods
    @Override
    public FileStoreDto buildFileStoreDtoFromUploadFileModel(@Nonnull final FileUploadModel model) {
        Assert.notNull(model);
        return new FileStoreDto(
                model.getInputStream(),
                model.getFileName(),
                model.getContentType(),
                new FileMetaDataDto(
                        model.getUuid(),
                        mapperFacade.map(model.getFileOrigin(), FileOrigin.class)
                )
        );
    }

    @Override
    public StoredFileInfoModel buildStoredFileInfoModelFromFileStoreData(@Nonnull final FileStoreData fileStoreData) {
        Assert.notNull(fileStoreData);
        return new StoredFileInfoModel(
                fileStoreData.getUuid(), fileStoreData.getFileName(), fileStoreData.getContentType(), fileStoreData.getCreated()
        );
    }

    @Override
    public List<StoredFileInfoModel> buildStoredFileInfoModelsFromFileStoreDataList(@Nonnull final List<FileStoreData> fileStoreDataList) {
        Assert.notNull(fileStoreDataList);
        final List<StoredFileInfoModel> filesInfo = new ArrayList<>();
        fileStoreDataList.forEach(fileStoreData -> filesInfo.add(buildStoredFileInfoModelFromFileStoreData(fileStoreData)));
        return filesInfo;
    }
    //endregion
}
