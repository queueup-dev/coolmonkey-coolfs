package com.sfl.coolmonkey.coolfs.api.model.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.coolfs.api.model.common.CommonErrorType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/11/15
 * Time: 10:34 PM
 */
public class ResultResponseModel<T extends AbstractResponseModel> implements Serializable {

    private static final long serialVersionUID = 6646600561716076786L;

    //region Properties
    @JsonProperty("errors")
    private Map<CommonErrorType, Object> errors;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("response")
    private T response;
    //endregion

    //region Constructors
    public ResultResponseModel() {
        this.errors = new EnumMap<>(CommonErrorType.class);
        this.success = true;
    }

    public ResultResponseModel(final T response) {
        this();
        this.response = response;
    }

    public ResultResponseModel(final Map<CommonErrorType, Object> errors) {
        this();
        this.errors = errors;
        this.success = false;
    }
    //endregion

    //region Public methods
    public boolean hasErrors() {
        return errors != null && errors.size() > 0;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResultResponseModel)) {
            return false;
        }
        final ResultResponseModel that = (ResultResponseModel) o;
        return new EqualsBuilder()
                .append(success, that.success)
                .append(errors, that.errors)
                .append(response, that.response)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(errors)
                .append(success)
                .append(response)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errors", errors)
                .append("success", success)
                .append("response", response)
                .toString();
    }
    //endregion

    //region Getters and setters
    public Map<CommonErrorType, Object> getErrors() {
        return errors;
    }

    public void setErrors(final Map<CommonErrorType, Object> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T result) {
        this.response = result;
    }
    //endregion
}
