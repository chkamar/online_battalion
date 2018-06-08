package cn.ch.battalion.core.dao;

import cn.ch.battalion.api.service.bean.HistoryBean;
import cn.ch.battalion.core.model.SearchHistory;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by karma on 2018/5/4.
 */
public interface SearchHistoryDao {
    //    List<HistoryBean> selects(@Param("userId") Long userId, @Param("limit") int limit);
    List<String> selects(@Param("userId") Long userId, @Param("userType") String userType, @Param("limit") int limit);

    void deleteById(Long id);

    void delete(String content);

    Long selectId(@Param("userId") Long userId, @Param("content") String content);

    void update(@Param("time") Date time, @Param("id") Long id);

    void insert(SearchHistory searchHistory);

    Integer selectCount();

    void deleteOld();
}
