package com.sfl.coolmonkey.coolfs.service.helper;

import com.sfl.coolmonkey.coolfs.service.storage.StorageService;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/3/15
 * Time: 4:07 PM
 */
@Component
public class ServiceIntegrationTestHelper extends CommonTestHelper {

    //region Dependencies
    @Autowired
    private StorageService storageService;
    //endregion

    //region Constructors
    public ServiceIntegrationTestHelper() {
    }
    //endregion

    //region Public methods

    //region Storage
    public FileStoreData createAndPersistFileStoreData(final FileStoreDto dto) {
        return storageService.getByMetaUuid(storageService.create(dto));
    }

    public FileStoreData createAndPersistFileStoreData() {
        return createAndPersistFileStoreData(createFileStoreDto());
    }
    //endregion

    //endregion
}
