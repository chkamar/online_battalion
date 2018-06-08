package cn.ch.battalion.core.model;

import cn.ch.battalion.api.base.model.BaseModel;

/**
 * Created by karma on 2018/5/10.
 */
public class Recommendation extends BaseModel {
    private Long battalionId;
    private Integer rank;

    public Long getBattalionId() {
        return battalionId;
    }

    public void setBattalionId(Long battalionId) {
        this.battalionId = battalionId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
