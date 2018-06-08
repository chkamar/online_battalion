package cn.ch.battalion.api.service.bean;


import cn.ch.battalion.core.model.Battalion;

/**
 * Created by karma on 2018/4/15.
 */
public class BattalionBean extends Battalion {
    private Long typeId;
    private String type;
//    private String typeImagePath;

//    public String getTypeImagePath() {
//        return typeImagePath;
//    }
//
//    public void setTypeImagePath(String typeImagePath) {
//        this.typeImagePath = typeImagePath;
//    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
