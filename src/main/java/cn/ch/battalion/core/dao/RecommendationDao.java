package cn.ch.battalion.core.dao;

import cn.ch.battalion.core.model.Recruitment;
import org.apache.ibatis.annotations.Param;

/**
 * Created by karma on 2018/5/10.
 */
public interface RecommendationDao {
    void add(@Param("battalionId") Long battalionId, @Param("rank") Integer rank);

    void delete(Long id);


}
