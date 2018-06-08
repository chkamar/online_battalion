package cn.ch.battalion.api.base.model;

import java.io.Serializable;

/**
 * Created by karma on 2018/4/1.
 */
public class BaseModel implements Serializable {
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
