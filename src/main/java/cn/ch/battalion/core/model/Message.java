package cn.ch.battalion.core.model;

import cn.ch.battalion.api.base.model.BaseModel;

import java.sql.Date;

/**
 * Created by karma on 2018/4/1.
 */
public class Message extends BaseModel {
    private Long userId;
    private String content;
    private Date time;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
