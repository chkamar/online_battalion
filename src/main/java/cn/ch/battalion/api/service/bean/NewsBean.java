package cn.ch.battalion.api.service.bean;

import cn.ch.battalion.core.model.News;

/**
 * Created by karma on 2018/4/5.
 */
public class NewsBean extends News {
    public final static int SUMMARY_LENGTH = 50;

    private String summary;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
