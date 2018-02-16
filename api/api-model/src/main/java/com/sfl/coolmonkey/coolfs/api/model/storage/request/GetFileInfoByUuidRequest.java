package com.sfl.coolmonkey.coolfs.api.model.storage.request;

import com.sfl.coolmonkey.coolfs.api.model.common.request.AbstractRequestModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.ws.rs.QueryParam;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 4:22 PM
 */
public class GetFileInfoByUuidRequest extends AbstractRequestModel {
    private static final long serialVersionUID = -5901059715123293955L;

    //region Properties
    @QueryParam("uuid")
    private String uuid;
    //endregion

    //region Constructors
    public GetFileInfoByUuidRequest() {
    }

    public GetFileInfoByUuidRequest(final String uuid) {
        this.uuid = uuid;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetFileInfoByUuidRequest)) {
            return false;
        }
        final GetFileInfoByUuidRequest that = (GetFileInfoByUuidRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(uuid, that.uuid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(uuid)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
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
    //endregion
}
