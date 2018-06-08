package cn.ch.battalion.api.service.bean;

/**
 * Created by karma on 2018/5/6.
 */
public class BattalionSearchBean {
    private Long typeId;
    private String name;
    private String sort;
    private String sc;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }
}
