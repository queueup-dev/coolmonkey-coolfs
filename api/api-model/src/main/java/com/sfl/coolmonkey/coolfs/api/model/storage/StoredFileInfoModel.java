package com.sfl.coolmonkey.coolfs.api.model.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.ApiModel;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 3:21 PM
 */
public class StoredFileInfoModel implements ApiModel {
    private static final long serialVersionUID = 2039577312557371090L;

    //region Properties
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("contentType")
    private String contentType;

    @JsonProperty("created")
    private Date created;
    //endregion

    //region Constructors
    public StoredFileInfoModel() {
    }

    public StoredFileInfoModel(final String uuid, final String fileName, final String contentType, final Date created) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
        this.created = ObjectUtils.clone(created);
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoredFileInfoModel)) {
            return false;
        }
        final StoredFileInfoModel that = (StoredFileInfoModel) o;
        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .append(fileName, that.fileName)
                .append(contentType, that.contentType)
                .append(created, that.created)
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
    //endregion

}
