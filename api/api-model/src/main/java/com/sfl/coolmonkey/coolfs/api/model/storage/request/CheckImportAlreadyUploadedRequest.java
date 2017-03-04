package com.sfl.coolmonkey.coolfs.api.model.storage.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.request.AbstractCompanyUuidAwareRequestModel;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 4/1/16
 * Time: 11:50 AM
 */
public class CheckImportAlreadyUploadedRequest extends AbstractCompanyUuidAwareRequestModel {
    private static final long serialVersionUID = 6948835325598089433L;

    //region Properties
    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("createdAfter")
    private Date createdAfter;
    //endregion

    //region Constructors
    public CheckImportAlreadyUploadedRequest() {
    }

    public CheckImportAlreadyUploadedRequest(final String companyUuid, final String fileName, final Date createdAfter) {
        super(companyUuid);
        this.fileName = fileName;
        this.createdAfter = ObjectUtils.clone(createdAfter);
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckImportAlreadyUploadedRequest)) {
            return false;
        }
        final CheckImportAlreadyUploadedRequest that = (CheckImportAlreadyUploadedRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(fileName, that.fileName)
                .append(createdAfter, that.createdAfter)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(fileName)
                .append(createdAfter)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fileName", fileName)
                .append("createdAfter", createdAfter)
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

    public Date getCreatedAfter() {
        return ObjectUtils.clone(createdAfter);
    }

    public void setCreatedAfter(final Date createdAfter) {
        this.createdAfter = ObjectUtils.clone(createdAfter);
    }
    //endregion
}
