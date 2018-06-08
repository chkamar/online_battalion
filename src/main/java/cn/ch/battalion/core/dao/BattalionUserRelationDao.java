package cn.ch.battalion.core.dao;


import cn.ch.battalion.api.service.bean.Enrollment;
import cn.ch.battalion.core.model.BattalionUserRelation;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by karma on 2018/4/12.
 */
public interface BattalionUserRelationDao {
    void insert(BattalionUserRelation battalionUserRelation);

    List<Enrollment> selectByBattalionId(Long battalionId);

    void deleteById(Long id);

    Date selectDeadlineByRelationId(Long relationId);

    Integer selectRecord(@Param("battalionId") Long battalionId, @Param("userId") Long userId);

    void deleteByBattalionId(@Param("battalionId") Long battalionId);

    Integer selectCount(@Param("battalionId") Long battalionId);

    Long selectBattalionId(Long id);

    void updateStatus(Long id);
}
