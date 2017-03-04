package com.sfl.coolmonkey.coolfs.api.model.storage.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.response.AbstractResponseModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 4/1/16
 * Time: 11:50 AM
 */
public class CheckImportAlreadyUploadedResponse extends AbstractResponseModel {
    private static final long serialVersionUID = 7275885462132174882L;

    //region Properties
    @JsonProperty("uuidList")
    private List<String> uuidList;
    //endregion

    //region Constructors
    public CheckImportAlreadyUploadedResponse() {
    }

    public CheckImportAlreadyUploadedResponse(List<String> uuidList) {
        this.uuidList = uuidList;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckImportAlreadyUploadedResponse)) {
            return false;
        }
        CheckImportAlreadyUploadedResponse that = (CheckImportAlreadyUploadedResponse) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(uuidList, that.uuidList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(uuidList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuidList", uuidList)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<String> getUuidList() {
        return uuidList;
    }

    public void setUuidList(List<String> uuidList) {
        this.uuidList = uuidList;
    }
    //endregion
}
