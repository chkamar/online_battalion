package cn.ch.battalion.core.model;

import cn.ch.battalion.api.base.model.BaseModel;

/**
 * Created by karma on 2018/5/16.
 */
public class Image extends BaseModel {
    private String path;
    private String type;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
