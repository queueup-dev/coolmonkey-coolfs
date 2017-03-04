package com.sfl.coolmonkey.coolfs.service.common.model;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/14/15
 * Time: 1:53 PM
 */
public abstract class AbstractDomainRootDocumentEntityModel extends AbstractDomainEntityModel {
    private static final long serialVersionUID = -1152667704652945755L;

    //region Properties
    @Id
    private ObjectId id;

    @Field("created")
    @Indexed
    private Date created;

    @Field("updated")
    @Indexed
    private Date updated;

    @Field("removed")
    @Indexed
    private Date removed;
    //endregion

    //region Constructors
    public AbstractDomainRootDocumentEntityModel() {
        created = new Date();
        updated = ObjectUtils.clone(created);
    }
    //endregion

    //region Utility methods
    public static ObjectId getIdOrNull(final AbstractDomainRootDocumentEntityModel entity) {
        return entity != null ? entity.getId() : null;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractDomainRootDocumentEntityModel)) {
            return false;
        }
        final AbstractDomainRootDocumentEntityModel that = (AbstractDomainRootDocumentEntityModel) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(created, that.created)
                .append(updated, that.updated)
                .append(removed, that.removed)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(created)
                .append(updated)
                .append(removed)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("created", created)
                .append("updated", updated)
                .append("removed", removed)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public Date getCreated() {
        return ObjectUtils.clone(created);
    }

    public void setCreated(final Date created) {
        this.created = ObjectUtils.clone(created);
    }

    public Date getUpdated() {
        return ObjectUtils.clone(updated);
    }

    public void setUpdated(final Date updated) {
        this.updated = ObjectUtils.clone(updated);
    }

    public Date getRemoved() {
        return ObjectUtils.clone(removed);
    }

    public void setRemoved(final Date removed) {
        this.removed = ObjectUtils.clone(removed);
    }
    //endregion
}
