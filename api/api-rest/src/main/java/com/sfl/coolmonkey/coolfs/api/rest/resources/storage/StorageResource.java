package com.sfl.coolmonkey.coolfs.api.rest.resources.storage;

import com.sfl.coolmonkey.coolfs.api.model.common.response.ResultResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileLoadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileOriginModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileUploadModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.GetFileInfoByUuidListRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.GetFileInfoByUuidRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.LoadFileByUuidRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.UploadFileRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.LoadFileByUuidResponse;
import com.sfl.coolmonkey.coolfs.facade.storage.StorageFacade;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 5:01 PM
 */
@Component
@Path("storage")
@Produces("application/json")
public class StorageResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageResource.class);

    //region Dependencies
    @Autowired
    private StorageFacade storageFacade;
    //endregion

    //region Constructors
    public StorageResource() {
        LOGGER.debug("Initializing storage resource");
    }
    //endregion

    //region Public methods
    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@FormDataParam("file") final InputStream inputStream,
                           @FormDataParam("file") final FormDataContentDisposition fileDetail,
                           @FormDataParam("file") FormDataBodyPart body) {
        final String fileName = body.getHeaders().getFirst("FileOrigin-Name");
        Assert.notNull(fileName, "The file name should not be null");
        final String contentType = body.getHeaders().getFirst("FileOrigin-MediaType");
        final String fileOriginString = body.getHeaders().getFirst("FileOrigin-FileOrigin");
        final String uploadFileMaxSize = body.getHeaders().getFirst("FileOrigin-UploadFile-MaxSize");
        final FileOriginModel fileOriginModel = StringUtils.isNotBlank(fileOriginString) ? FileOriginModel.valueOf(fileOriginString) : null;
        final Long uploadFileMaxSizeLong = StringUtils.isNotBlank(uploadFileMaxSize) ? Long.valueOf(uploadFileMaxSize) : null;
        final FileUploadModel fileUploadModel = new FileUploadModel(inputStream, decodeUrlEncodedString(fileName), contentType, fileOriginModel);
        final UploadFileRequest uploadFileRequest = new UploadFileRequest(fileUploadModel);
        uploadFileRequest.setMaxFileLength(uploadFileMaxSizeLong);
        return Response.ok(storageFacade.upload(uploadFileRequest)).build();
    }

    @GET
    @Path("by-uuid")
    public Response getFileInfoByUuid(@BeanParam final GetFileInfoByUuidRequest request) {
        return Response.ok(storageFacade.getFileInfoByUuid(request)).build();
    }

    @POST
    @Path("by-uuids")
    public Response getFileInfoByUuids(final GetFileInfoByUuidListRequest request) {
        return Response.ok(storageFacade.getFileInfoByUuids(request)).build();
    }

    @GET
    @Path("download-by-uuid")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileByUuid(@BeanParam final LoadFileByUuidRequest request) {
        final ResultResponseModel<LoadFileByUuidResponse> result = storageFacade.loadFileByUuid(request);
        final FileLoadModel loadFileModel = result.getResponse().getLoadFileModel();
        return Response.ok(loadFileModel.getInputStream())
                .header("Content-Disposition", "attachment; filename=" + loadFileModel.getFileName())
                .header("FileOrigin-Name", loadFileModel.getFileName())
                .header("FileOrigin-MediaType", loadFileModel.getContentType())
                .build();
    }

    @GET
    @Path("load-by-uuid")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response loadFileByUuid(@BeanParam final LoadFileByUuidRequest request) {
        final ResultResponseModel<LoadFileByUuidResponse> result = storageFacade.loadFileByUuid(request);
        final FileLoadModel loadFileModel = result.getResponse().getLoadFileModel();
        return Response.ok(loadFileModel.getInputStream(), loadFileModel.getContentType()).build();
    }

    @GET
    @Path("heartbeat")
    public Response getHeartBeat() {
        return Response.ok("OK").build();
    }
    ///endregion

    //region Utility methods
    @Nullable
    private String decodeUrlEncodedString(final String encodedString) {
        try {
            return new URLCodec().decode(encodedString);
        } catch (final DecoderException ignore) {
            // Ignore
        }
        return null;
    }
    //endregion

}
