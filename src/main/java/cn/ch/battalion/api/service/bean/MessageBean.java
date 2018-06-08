package cn.ch.battalion.api.service.bean;


import cn.ch.battalion.core.model.Message;

/**
 * Created by karma on 2018/4/5.
 */
public class MessageBean extends Message {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
