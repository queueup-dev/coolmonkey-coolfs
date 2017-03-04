package com.sfl.coolmonkey.coolfs.api.model.storage.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.response.AbstractResponseModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 5:03 PM
 */
public class GetFileInfoByUuidListRequest extends AbstractResponseModel {
    private static final long serialVersionUID = -467496891116246166L;

    //region Properties
    @JsonProperty("uuids")
    private List<String> uuids;
    //endregion

    //region Constructors
    public GetFileInfoByUuidListRequest() {
    }

    public GetFileInfoByUuidListRequest(final List<String> uuids) {
        this.uuids = uuids;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetFileInfoByUuidListRequest)) {
            return false;
        }
        final GetFileInfoByUuidListRequest that = (GetFileInfoByUuidListRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(uuids, that.uuids)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(uuids)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuids", uuids)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<String> getUuids() {
        return uuids;
    }

    public void setUuids(final List<String> uuids) {
        this.uuids = uuids;
    }
    //endregion
}
