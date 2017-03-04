package com.sfl.coolmonkey.coolfs.facade.storage.impl;

import com.sfl.coolmonkey.commons.api.model.CommonErrorType;
import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileLoadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.*;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.*;
import com.sfl.coolmonkey.coolfs.facade.storage.StorageFacade;
import com.sfl.coolmonkey.coolfs.facade.storage.component.StorageFacadeConversionComponent;
import com.sfl.coolmonkey.coolfs.facade.storage.component.StorageFacadeValidationComponent;
import com.sfl.coolmonkey.coolfs.service.storage.StorageService;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileOrigin;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        final String companyUuid = request.getCompanyUuid();
        final FileStoreDto dto = storageFacadeConversionComponent.buildFileStoreDtoFromUploadFileModel(request.getModel());
        dto.getFileMetaDataDto().setCompanyUuid(companyUuid);
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

    @Nonnull
    @Override
    public ResultResponseModel<CheckImportAlreadyUploadedResponse> checkImportAlreadyUploaded(@Nonnull final CheckImportAlreadyUploadedRequest request) {
        assertCheckImportAlreadyUploadedRequest(request);
        // Unwrap arguments
        final String companyUuid = request.getCompanyUuid();
        final Date createdAfter = request.getCreatedAfter();
        final String fileName = request.getFileName();
        final List<FileStoreData> fileStoreDatas = storageService.getByCompanyUuidAndFileNameAndCreatedAfterAndOrigin(companyUuid, fileName, createdAfter, FileOrigin.IMPORT_CSV);
        final List<String> uuids = fileStoreDatas.stream().map(FileStoreData::getUuid).collect(Collectors.toList());
        return new ResultResponseModel<>(new CheckImportAlreadyUploadedResponse(uuids));
    }
    //endregion

    //region Utility methods
    private void assertCheckImportAlreadyUploadedRequest(final CheckImportAlreadyUploadedRequest request) {
        Assert.notNull(request);
        Assert.notNull(request.getCompanyUuid());
        Assert.notNull(request.getCreatedAfter());
        Assert.notNull(request.getFileName());
    }

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
