package com.sfl.coolmonkey.coolfs.api.model.storage.request;

import com.sfl.coolmonkey.coolfs.api.model.common.request.AbstractRequestModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileUploadModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 3:20 PM
 */
public class UploadFileRequest extends AbstractRequestModel {
    private static final long serialVersionUID = 4999298567725747520L;

    //region Properties
    private FileUploadModel model;

    private Long maxFileLength;
    //endregion

    //region Constructors
    public UploadFileRequest() {
    }

    public UploadFileRequest(final FileUploadModel model) {
        this.model = model;
    }

    public UploadFileRequest(final FileUploadModel model, final Long maxFileLength) {
        this.model = model;
        this.maxFileLength = maxFileLength;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UploadFileRequest)) {
            return false;
        }
        final UploadFileRequest that = (UploadFileRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(model, that.model)
                .append(maxFileLength, that.maxFileLength)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(model)
                .append(maxFileLength)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("model", model)
                .append("maxFileLength", maxFileLength)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public FileUploadModel getModel() {
        return model;
    }

    public void setModel(final FileUploadModel model) {
        this.model = model;
    }

    public Long getMaxFileLength() {
        return maxFileLength;
    }

    public void setMaxFileLength(final Long maxFileLength) {
        this.maxFileLength = maxFileLength;
    }
    //endregion
}
