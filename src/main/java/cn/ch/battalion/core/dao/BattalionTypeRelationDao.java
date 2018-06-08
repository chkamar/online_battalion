package cn.ch.battalion.core.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by karma on 2018/5/23.
 */
public interface BattalionTypeRelationDao {
    void insert(@Param("battalionId") Long battalionId, @Param("typeId") Long typeId);

    void delete(@Param("battalionId") Long battalionId);
}
