package com.sfl.coolmonkey.coolfs.api.model.storage.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.response.AbstractResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 4:23 PM
 */
public class GetFileInfoByUuidResponse extends AbstractResponseModel {
    private static final long serialVersionUID = 2895699834980229132L;

    //region Properties
    @JsonProperty("fileInfo")
    private StoredFileInfoModel fileInfo;
    //endregion

    //region Constructors
    public GetFileInfoByUuidResponse() {
    }

    public GetFileInfoByUuidResponse(final StoredFileInfoModel fileInfo) {
        this.fileInfo = fileInfo;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetFileInfoByUuidResponse)) {
            return false;
        }
        final GetFileInfoByUuidResponse that = (GetFileInfoByUuidResponse) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(fileInfo, that.fileInfo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(fileInfo)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fileInfo", fileInfo)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public StoredFileInfoModel getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(final StoredFileInfoModel fileInfo) {
        this.fileInfo = fileInfo;
    }
    //endregion
}
