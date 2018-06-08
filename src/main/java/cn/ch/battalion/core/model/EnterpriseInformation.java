package cn.ch.battalion.core.model;

import cn.ch.battalion.api.base.model.BaseModel;

/**
 * Created by karma on 2018/4/1.
 */
public class EnterpriseInformation extends BaseModel {
    private String name;
    private String summary;
    private String address;
    private String officalTelephone;
    private String hrTelephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOfficalTelephone() {
        return officalTelephone;
    }

    public void setOfficalTelephone(String officalTelephone) {
        this.officalTelephone = officalTelephone;
    }

    public String getHrTelephone() {
        return hrTelephone;
    }

    public void setHrTelephone(String hrTelephone) {
        this.hrTelephone = hrTelephone;
    }
}
