package cn.ch.battalion.api.service;

import cn.ch.battalion.api.service.bean.HistoryBean;
import cn.ch.battalion.core.model.SearchHistory;

import java.util.List;

/**
 * Created by karma on 2018/5/4.
 */
public interface SearchHistoryService {
    List<String> get(Long userId, String userType, int limit);

//    List<HistoryBean> get(Long userId,int limit);

    void delete(Long id);

    void delete(String content);

    void upsert(SearchHistory searchHistory);
}
