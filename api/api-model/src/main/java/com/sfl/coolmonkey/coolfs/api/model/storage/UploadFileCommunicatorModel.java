package com.sfl.coolmonkey.coolfs.api.model.storage;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.InputStream;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/19/16
 * Time: 7:50 PM
 */
public class UploadFileCommunicatorModel {

    //region Properties
    private String fileName;

    private String contentType;

    private InputStream inputStream;
    //endregion

    //region Constructors
    public UploadFileCommunicatorModel() {
    }

    public UploadFileCommunicatorModel(final String fileName, final String contentType, final InputStream inputStream) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UploadFileCommunicatorModel)) {
            return false;
        }
        final UploadFileCommunicatorModel that = (UploadFileCommunicatorModel) o;
        return new EqualsBuilder()
                .append(fileName, that.fileName)
                .append(contentType, that.contentType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(fileName)
                .append(contentType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fileName", fileName)
                .append("contentType", contentType)
                .toString();
    }
    //endregion

    //region Properties getters and setters
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }
    //endregion
}
