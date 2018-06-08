package cn.ch.battalion.api.service.bean;

import java.util.List;

/**
 * Created by karma on 2018/5/26.
 */
public class EnrollBean {
    private String name;
    private Integer count;
    private List<Enrollment> enrollments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
