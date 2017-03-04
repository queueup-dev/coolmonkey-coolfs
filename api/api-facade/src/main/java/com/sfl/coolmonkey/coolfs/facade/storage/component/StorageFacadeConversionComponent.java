package com.sfl.coolmonkey.coolfs.facade.storage.component;

import com.sfl.coolmonkey.coolfs.api.model.storage.FileUploadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 7:36 PM
 */
public interface StorageFacadeConversionComponent {
    FileStoreDto buildFileStoreDtoFromUploadFileModel(@Nonnull final FileUploadModel model);

    StoredFileInfoModel buildStoredFileInfoModelFromFileStoreData(@Nonnull final FileStoreData fileStoreData);

    List<StoredFileInfoModel> buildStoredFileInfoModelsFromFileStoreDataList(@Nonnull final List<FileStoreData> fileStoreDataList);
}
