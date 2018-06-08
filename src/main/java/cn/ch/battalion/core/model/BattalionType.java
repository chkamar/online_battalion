package cn.ch.battalion.core.model;

import cn.ch.battalion.api.base.model.BaseModel;

/**
 * Created by karma on 2018/4/15.
 */
public class BattalionType extends BaseModel {
    private String name;
//    private String imagePath;

//    public BattalionType(String name, String imagePath) {
//        this.name = name;
//        this.imagePath = imagePath;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getImagePath() {
//        return imagePath;
//    }
//
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }
}
