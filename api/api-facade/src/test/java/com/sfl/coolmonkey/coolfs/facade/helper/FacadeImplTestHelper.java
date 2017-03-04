package com.sfl.coolmonkey.coolfs.facade.helper;

import com.sfl.coolmonkey.commons.api.model.CommonErrorType;
import com.sfl.coolmonkey.commons.api.model.response.AbstractResponseModel;
import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileLoadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileOriginModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileUploadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import com.sfl.coolmonkey.coolfs.service.helper.CommonTestHelper;
import org.apache.commons.io.IOUtils;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/20/15
 * Time: 2:51 PM
 */
public final class FacadeImplTestHelper extends CommonTestHelper {

    //region Constants
    //endregion

    //region Constructors
    public FacadeImplTestHelper() {
    }
    //endregion

    //region Public methods
    public void assertValidationErrors(final ResultResponseModel<? extends AbstractResponseModel> result,
                                       final Set<CommonErrorType> expectedErrors) {
        assertNotNull(result);
        assertEquals(expectedErrors.size(), result.getErrors().size());
        result.getErrors().forEach((key, value) -> assertTrue(expectedErrors.contains(key)));
    }

    public StoredFileInfoModel createStoredFileInfoModel() {
        return new StoredFileInfoModel(UUID.randomUUID().toString(), "test.jpg", "image/jpg", new Date());
    }

    public FileUploadModel createFileUploadModel() {
        return new FileUploadModel(
                IOUtils.toInputStream("some test data for my input stream"),
                UUID.randomUUID().toString(),
                "test.jpg",
                "image/jpg",
                FileOriginModel.IMPORT_CSV
        );
    }

    public FileLoadModel createFileLoadModel() {
        return new FileLoadModel(
                IOUtils.toInputStream("some test data for my input stream"), "test.jpg", "image/jpg"
        );
    }
    //endregion
}
