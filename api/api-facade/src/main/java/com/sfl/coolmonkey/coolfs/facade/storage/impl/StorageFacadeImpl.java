package com.sfl.coolmonkey.coolfs.facade.storage.impl;

import com.sfl.coolmonkey.coolfs.api.model.common.CommonErrorType;
import com.sfl.coolmonkey.coolfs.api.model.common.response.ResultResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileLoadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.GetFileInfoByUuidListRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.GetFileInfoByUuidRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.LoadFileByUuidRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.UploadFileRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.GetFileInfoByUuidListResponse;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.GetFileInfoByUuidResponse;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.LoadFileByUuidResponse;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.UploadFileResponse;
import com.sfl.coolmonkey.coolfs.facade.storage.StorageFacade;
import com.sfl.coolmonkey.coolfs.facade.storage.component.StorageFacadeConversionComponent;
import com.sfl.coolmonkey.coolfs.facade.storage.component.StorageFacadeValidationComponent;
import com.sfl.coolmonkey.coolfs.service.storage.StorageService;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 3:50 PM
 */
@Component
public class StorageFacadeImpl implements StorageFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageFacadeImpl.class);

    //region Dependencies
    @Autowired
    private StorageService storageService;

    @Autowired
    private StorageFacadeConversionComponent storageFacadeConversionComponent;

    @Autowired
    private StorageFacadeValidationComponent storageFacadeValidationComponent;
    //endregion

    //region Constructors
    public StorageFacadeImpl() {
        LOGGER.debug("Initializing storage facade");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public ResultResponseModel<UploadFileResponse> upload(@Nonnull final UploadFileRequest request) {
        assertUploadFileRequest(request);
        final FileStoreDto dto = storageFacadeConversionComponent.buildFileStoreDtoFromUploadFileModel(request.getModel());
        final String uuid = storageService.create(dto);
        final FileStoreData fileStoreData = storageService.getByMetaUuid(uuid);
        final Map<CommonErrorType, Object> errors = storageFacadeValidationComponent.validateFileMaxLength(fileStoreData, request.getMaxFileLength());
        if (!errors.isEmpty()) {
            storageService.deleteByMetaUuid(uuid);
            return new ResultResponseModel<>(errors);
        }
        final StoredFileInfoModel fileInfo = storageFacadeConversionComponent.buildStoredFileInfoModelFromFileStoreData(fileStoreData);
        return new ResultResponseModel<>(new UploadFileResponse(fileInfo));
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetFileInfoByUuidResponse> getFileInfoByUuid(@Nonnull final GetFileInfoByUuidRequest request) {
        assertGetFileInfoByUuidRequest(request);
        final FileStoreData fileStoreData = storageService.getByMetaUuid(request.getUuid());
        final StoredFileInfoModel fileInfo = storageFacadeConversionComponent.buildStoredFileInfoModelFromFileStoreData(fileStoreData);
        return new ResultResponseModel<>(new GetFileInfoByUuidResponse(fileInfo));
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetFileInfoByUuidListResponse> getFileInfoByUuids(@Nonnull final GetFileInfoByUuidListRequest request) {
        assertGetFileInfoByUuidListRequest(request);
        final List<FileStoreData> fileStoreDataList = storageService.getByMetaUuids(request.getUuids());
        final List<StoredFileInfoModel> filesInfo = storageFacadeConversionComponent.buildStoredFileInfoModelsFromFileStoreDataList(fileStoreDataList);
        return new ResultResponseModel<>(new GetFileInfoByUuidListResponse(filesInfo));
    }

    @Nonnull
    @Override
    public ResultResponseModel<LoadFileByUuidResponse> loadFileByUuid(@Nonnull final LoadFileByUuidRequest request) {
        assertLoadFileByUuidRequest(request);
        final FileStoreData fileStoreData = storageService.getByMetaUuid(request.getUuid());
        final FileLoadModel fileLoadModel = new FileLoadModel(fileStoreData.getInputStream(), fileStoreData.getFileName(), fileStoreData.getContentType());
        return new ResultResponseModel<>(new LoadFileByUuidResponse(fileLoadModel));
    }
    //endregion

    //region Utility methods
    private void assertUploadFileRequest(final UploadFileRequest request) {
        Assert.notNull(request);
        Assert.notNull(request.getModel());
        Assert.notNull(request.getModel().getInputStream());
        Assert.notNull(request.getModel().getFileName());
        Assert.notNull(request.getModel().getFileOrigin());
    }

    private void assertGetFileInfoByUuidRequest(final GetFileInfoByUuidRequest request) {
        Assert.notNull(request);
        Assert.notNull(request.getUuid());
    }

    private void assertGetFileInfoByUuidListRequest(final GetFileInfoByUuidListRequest request) {
        Assert.notNull(request);
        Assert.notNull(request.getUuids());
        Assert.noNullElements(request.getUuids().toArray());
    }

    private void assertLoadFileByUuidRequest(final LoadFileByUuidRequest request) {
        Assert.notNull(request);
        Assert.notNull(request.getUuid());
    }
    //endregion
}
