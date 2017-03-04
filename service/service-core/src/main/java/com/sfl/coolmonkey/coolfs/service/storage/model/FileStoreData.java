package com.sfl.coolmonkey.coolfs.service.storage.model;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.InputStream;
import java.util.Date;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 7:12 PM
 */
public class FileStoreData {

    //region Properties
    private String uuid;

    private String fileName;

    private String contentType;

    private Date created;

    private InputStream inputStream;

    private long length;
    //endregion

    //region Constructors
    public FileStoreData() {
    }

    public FileStoreData(final String uuid,
                         final String fileName,
                         final String contentType,
                         final Date created,
                         final InputStream inputStream,
                         final long length) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
        this.created = ObjectUtils.clone(created);
        this.inputStream = inputStream;
        this.length = length;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileStoreData)) {
            return false;
        }
        final FileStoreData that = (FileStoreData) o;
        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .append(fileName, that.fileName)
                .append(contentType, that.contentType)
                .append(created, that.created)
                .append(length, that.length)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uuid)
                .append(fileName)
                .append(contentType)
                .append(created)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("fileName", fileName)
                .append("contentType", contentType)
                .append("created", created)
                .append("length", length)
                .toString();
    }
    //endregion

    //region Properties getters and setters
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

    public Date getCreated() {
        return ObjectUtils.clone(created);
    }

    public void setCreated(final Date created) {
        this.created = ObjectUtils.clone(created);
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getLength() {
        return length;
    }

    public void setLength(final long length) {
        this.length = length;
    }
    //endregion
}
