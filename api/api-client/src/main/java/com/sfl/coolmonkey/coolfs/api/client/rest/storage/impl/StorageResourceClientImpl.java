package com.sfl.coolmonkey.coolfs.api.client.rest.storage.impl;

import com.sfl.coolmonkey.coolfs.api.client.rest.common.AbstractResourceClient;
import com.sfl.coolmonkey.coolfs.api.client.rest.storage.StorageResourceClient;
import com.sfl.coolmonkey.coolfs.api.model.common.response.ResultResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileLoadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileUploadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.GetFileInfoByUuidListRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.GetFileInfoByUuidRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.LoadFileByUuidRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.UploadFileRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.GetFileInfoByUuidListResponse;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.GetFileInfoByUuidResponse;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.LoadFileByUuidResponse;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.UploadFileResponse;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 6:50 PM
 */
public class StorageResourceClientImpl extends AbstractResourceClient implements StorageResourceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageResourceClientImpl.class);

    //region Constants
    private static final String RESOURCE_BASE_PATH = "storage";
    private static final String GET_INFO_BY_UUID_PATH = "by-uuid";
    private static final String GET_INFO_BY_UUIDS_PATH = "by-uuids";
    private static final String UPLOAD_FILE_PATH = "upload";
    private static final String DOWNLOAD_FILE_PATH = "download-by-uuid";

    private static final String PREFIX = "COOLMONKEY_STORAGE_RESOURCE_CLIENT";
    private static final String SUFFIX = ".tmp";
    //endregion

    //region Constructors
    public StorageResourceClientImpl(final Client client, final String apiPath) {
        super(client, apiPath);
        LOGGER.debug("Initializing");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public ResultResponseModel<UploadFileResponse> upload(@Nonnull final UploadFileRequest request) {
        try (final MultiPart multiPart = new MultiPart()) {
            final FileUploadModel model = request.getModel();
            final FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", streamToFile(model.getInputStream()), MediaType.MULTIPART_FORM_DATA_TYPE);
            fileDataBodyPart.getHeaders().add("FileOrigin-Name", model.getFileName());
            fileDataBodyPart.getHeaders().add("FileOrigin-MediaType", model.getContentType());
            fileDataBodyPart.getHeaders().add("FileOrigin-FileOrigin", model.getFileOrigin().toString());
            fileDataBodyPart.getHeaders().add("FileOrigin-UploadFile-MaxSize", request.getMaxFileLength() != null ? request.getMaxFileLength().toString() : "");
            multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
            multiPart.bodyPart(fileDataBodyPart);
            return getClient()
                    .target(getApiPath())
                    .path(RESOURCE_BASE_PATH)
                    .path(UPLOAD_FILE_PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(multiPart, multiPart.getMediaType()), new GenericType<ResultResponseModel<UploadFileResponse>>() {
                    });
        } catch (final IOException e) {
            LOGGER.error("Unexpected IO exception occurs in coolfs - {}", e);
        }
        LOGGER.error("Can not handle request - {}", request);
        throw new IllegalArgumentException("Can not handle request - " + request);
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetFileInfoByUuidResponse> getFileInfoByUuid(@Nonnull final GetFileInfoByUuidRequest request) {
        Assert.notNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path(GET_INFO_BY_UUID_PATH)
                .queryParam("uuid", request.getUuid())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ResultResponseModel<GetFileInfoByUuidResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetFileInfoByUuidListResponse> getFileInfoByUuids(@Nonnull final GetFileInfoByUuidListRequest request) {
        Assert.notNull(request);
        return getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path(GET_INFO_BY_UUIDS_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), new GenericType<ResultResponseModel<GetFileInfoByUuidListResponse>>() {
                });
    }

    @Nonnull
    @Override
    public ResultResponseModel<LoadFileByUuidResponse> downloadFileByUuid(@Nonnull final LoadFileByUuidRequest request) {
        Assert.notNull(request);
        final Response post = getClient()
                .target(getApiPath())
                .path(RESOURCE_BASE_PATH)
                .path(DOWNLOAD_FILE_PATH)
                .queryParam("uuid", request.getUuid())
                .request()
                .get();
        final FileLoadModel fileLoadModel = new FileLoadModel(
                post.readEntity(InputStream.class),
                post.getHeaderString("FileOrigin-Name"),
                post.getHeaderString("FileOrigin-MediaType")
        );
        return new ResultResponseModel<>(new LoadFileByUuidResponse(fileLoadModel));
    }
    //endregion

    //region Utility methods
    private static File streamToFile(final InputStream in) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile(PREFIX, SUFFIX);
            tempFile.deleteOnExit();
            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                IOUtils.copy(in, out);
            }
        } catch (final IOException ignore) {
            // Ignore
        }
        return tempFile;
    }
    //endregion
}
