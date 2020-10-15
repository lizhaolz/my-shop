package com.lizhao.my.shop.commons.persistence;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable {
    private Long id;
    private Date created;
    private Date updated;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
}
