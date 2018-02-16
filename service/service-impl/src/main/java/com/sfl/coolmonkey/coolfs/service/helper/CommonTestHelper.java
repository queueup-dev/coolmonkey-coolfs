package com.sfl.coolmonkey.coolfs.service.helper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileMetaDataDto;
import com.sfl.coolmonkey.coolfs.service.storage.dto.FileStoreDto;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileOrigin;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileStoreData;
import org.apache.commons.io.IOUtils;

import java.util.Date;
import java.util.UUID;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 8/21/15
 * Time: 11:24 AM
 */
@SuppressWarnings({
        "MagicNumber",
        "DuplicateStringLiteralInspection",
        "checkstyle:com.puppycrawl.tools.checkstyle.checks.coding.MagicNumberCheck",
        "pmd:AvoidDuplicateLiterals",
        "squid:S1192"
})
public class CommonTestHelper {

    //region Public methods

    //region File store
    public FileMetaDataDto createFileMetaDataDto() {
        return new FileMetaDataDto(UUID.randomUUID().toString(), FileOrigin.IMPORT_CSV);
    }

    public FileStoreDto createFileStoreDto() {
        return new FileStoreDto(
                IOUtils.toInputStream("some test data for my input stream"),
                "test.png",
                "image/jpg",
                createFileMetaDataDto()
        );
    }

    public FileStoreDto createFileStoreDto(final String streamString) {
        return new FileStoreDto(
                IOUtils.toInputStream(streamString),
                "test.png",
                "image/jpg",
                createFileMetaDataDto()
        );
    }

    public FileStoreData createFileStoreData() {
        return new FileStoreData(
                UUID.randomUUID().toString(),
                "test.jpg",
                "image/jpg",
                new Date(),
                IOUtils.toInputStream("some test data for my input stream"),
                35L
        );
    }

    public FileStoreData createFileStoreData(final String uuid) {
        return new FileStoreData(
                uuid,
                "test.jpg",
                "image/jpg",
                new Date(),
                IOUtils.toInputStream("some test data for my input stream"),
                35L
        );
    }

    public GridFSDBFile createGridFSDBFile() {
        final GridFSDBFile gridFSDBFile = new GridFSDBFile();
        final DBObject dbObject = new BasicDBObject();
        dbObject.put("uuid", UUID.randomUUID().toString());
        gridFSDBFile.setMetaData(dbObject);
        return gridFSDBFile;
    }

    public GridFSDBFile createGridFSDBFile(final String uuid) {
        final GridFSDBFile gridFSDBFile = new GridFSDBFile();
        final DBObject dbObject = new BasicDBObject();
        dbObject.put("uuid", uuid);
        gridFSDBFile.setMetaData(dbObject);
        return gridFSDBFile;
    }
    //endregion

    //endregion
}
