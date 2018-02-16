package com.sfl.coolmonkey.coolfs.api.model.storage.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.coolfs.api.model.common.response.AbstractResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 5:05 PM
 */
public class GetFileInfoByUuidListResponse extends AbstractResponseModel {
    private static final long serialVersionUID = -6441538328102876119L;

    //region Properties
    @JsonProperty("filesInfo")
    private List<StoredFileInfoModel> filesInfo;
    //endregion

    //region Constructors
    public GetFileInfoByUuidListResponse() {
    }

    public GetFileInfoByUuidListResponse(final List<StoredFileInfoModel> filesInfo) {
        this.filesInfo = filesInfo;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetFileInfoByUuidListResponse)) {
            return false;
        }
        final GetFileInfoByUuidListResponse that = (GetFileInfoByUuidListResponse) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(filesInfo, that.filesInfo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(filesInfo)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("filesInfo", filesInfo)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<StoredFileInfoModel> getFilesInfo() {
        return filesInfo;
    }

    public void setFilesInfo(final List<StoredFileInfoModel> filesInfo) {
        this.filesInfo = filesInfo;
    }
    //endregion
}
