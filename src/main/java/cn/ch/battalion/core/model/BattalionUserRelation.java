package cn.ch.battalion.core.model;

import cn.ch.battalion.api.base.model.BaseModel;

import java.sql.Date;

/**
 * Created by karma on 2018/4/1.
 */
public class BattalionUserRelation extends BaseModel {
    private Long battalionId;
    private Long userId;
    private Date time;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getBattalionId() {
        return battalionId;
    }

    public void setBattalionId(Long battalionId) {
        this.battalionId = battalionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
