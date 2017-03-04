package com.sfl.coolmonkey.coolfs.api.model.storage;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.InputStream;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 5:44 PM
 */
public class FileLoadModel {

    //region Properties
    private transient InputStream inputStream;

    private String fileName;

    private String contentType;
    //endregion

    //region Constructors
    public FileLoadModel() {
    }

    public FileLoadModel(final InputStream inputStream, final String fileName, final String contentType) {
        this.inputStream = inputStream;
        this.fileName = fileName;
        this.contentType = contentType;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileLoadModel)) {
            return false;
        }
        final FileLoadModel that = (FileLoadModel) o;
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
    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
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
    //endregion
}
