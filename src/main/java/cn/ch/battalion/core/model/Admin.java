package cn.ch.battalion.core.model;

import cn.ch.battalion.api.base.model.BaseModel;

/**
 * Created by karma on 2018/4/1.
 */
public class Admin extends BaseModel {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
