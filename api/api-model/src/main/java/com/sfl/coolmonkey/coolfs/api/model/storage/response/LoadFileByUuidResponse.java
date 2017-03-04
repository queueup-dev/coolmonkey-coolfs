package com.sfl.coolmonkey.coolfs.api.model.storage.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.response.AbstractResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.FileLoadModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/18/16
 * Time: 5:49 PM
 */
public class LoadFileByUuidResponse extends AbstractResponseModel {
    private static final long serialVersionUID = -1929553663267738781L;

    //region Properties
    @JsonProperty("loadFile")
    private FileLoadModel loadFileModel;
    //endregion

    //region Constructors
    public LoadFileByUuidResponse() {
    }

    public LoadFileByUuidResponse(final FileLoadModel loadFileModel) {
        this.loadFileModel = loadFileModel;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoadFileByUuidResponse)) {
            return false;
        }
        final LoadFileByUuidResponse that = (LoadFileByUuidResponse) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(loadFileModel, that.loadFileModel)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(loadFileModel)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("loadFileModel", loadFileModel)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public FileLoadModel getLoadFileModel() {
        return loadFileModel;
    }

    public void setLoadFileModel(final FileLoadModel loadFileModel) {
        this.loadFileModel = loadFileModel;
    }
    //endregion
}
