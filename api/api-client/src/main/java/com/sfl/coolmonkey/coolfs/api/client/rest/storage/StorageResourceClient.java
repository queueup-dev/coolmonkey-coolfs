package com.sfl.coolmonkey.coolfs.api.client.rest.storage;

import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.*;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.*;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 6:49 PM
 */
public interface StorageResourceClient {
    @Nonnull
    ResultResponseModel<UploadFileResponse> upload(@Nonnull final UploadFileRequest request);

    @Nonnull
    ResultResponseModel<GetFileInfoByUuidResponse> getFileInfoByUuid(@Nonnull final GetFileInfoByUuidRequest request);

    @Nonnull
    ResultResponseModel<GetFileInfoByUuidListResponse> getFileInfoByUuids(@Nonnull final GetFileInfoByUuidListRequest request);

    @Nonnull
    ResultResponseModel<LoadFileByUuidResponse> downloadFileByUuid(@Nonnull final LoadFileByUuidRequest request);

    @Nonnull
    ResultResponseModel<CheckImportAlreadyUploadedResponse> checkImportAlreadyUploaded(@Nonnull final CheckImportAlreadyUploadedRequest request);
}
