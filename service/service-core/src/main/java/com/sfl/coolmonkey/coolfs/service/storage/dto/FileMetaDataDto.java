package com.sfl.coolmonkey.coolfs.service.storage.dto;

import com.sfl.coolmonkey.coolfs.service.storage.model.FileOrigin;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/17/16
 * Time: 7:09 PM
 */
public class FileMetaDataDto implements Serializable {
    private static final long serialVersionUID = -7496303487412416236L;

    //region Properties
    private String uuid;

    private FileOrigin fileOrigin;
    //endregion

    //region Constructors
    public FileMetaDataDto() {
    }

    public FileMetaDataDto(final String uuid, final FileOrigin fileOrigin) {
        this.uuid = uuid;
        this.fileOrigin = fileOrigin;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileMetaDataDto)) {
            return false;
        }
        final FileMetaDataDto that = (FileMetaDataDto) o;
        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .append(fileOrigin, that.fileOrigin)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uuid)
                .append(fileOrigin)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("fileOrigin", fileOrigin)
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

    public FileOrigin getFileOrigin() {
        return fileOrigin;
    }

    public void setFileOrigin(final FileOrigin fileOrigin) {
        this.fileOrigin = fileOrigin;
    }
    //endregion
}
