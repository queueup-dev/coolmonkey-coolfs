package com.sfl.coolmonkey.coolfs.api.model.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.InputStream;
import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 3:07 PM
 */
public class FileUploadModel implements Serializable {
    private static final long serialVersionUID = 1156819389959402634L;

    //region Properties
    private transient InputStream inputStream;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("contentType")
    private String contentType;

    @JsonProperty("fileOrigin")
    private FileOriginModel fileOrigin;
    //endregion

    //region Constructors
    public FileUploadModel() {
    }

    public FileUploadModel(final InputStream inputStream, final String fileName, final String contentType, final FileOriginModel fileOrigin) {
        this.inputStream = inputStream;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileOrigin = fileOrigin;
    }

    public FileUploadModel(final InputStream inputStream, final String uuid, final String fileName, final String contentType, final FileOriginModel fileOrigin) {
        this.inputStream = inputStream;
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileOrigin = fileOrigin;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileUploadModel)) {
            return false;
        }
        final FileUploadModel that = (FileUploadModel) o;
        return new EqualsBuilder()
                .append(inputStream, that.inputStream)
                .append(uuid, that.uuid)
                .append(fileName, that.fileName)
                .append(contentType, that.contentType)
                .append(fileOrigin, that.fileOrigin)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(inputStream)
                .append(uuid)
                .append(fileName)
                .append(contentType)
                .append(fileOrigin)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("inputStream", inputStream)
                .append("uuid", uuid)
                .append("fileName", fileName)
                .append("contentType", contentType)
                .append("fileOrigin", fileOrigin)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public FileOriginModel getFileOrigin() {
        return fileOrigin;
    }

    public void setFileOrigin(final FileOriginModel fileOrigin) {
        this.fileOrigin = fileOrigin;
    }
    //endregion
}
