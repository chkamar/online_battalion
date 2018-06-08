package cn.ch.battalion.core.dao;


import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.service.bean.BattalionBean;
import cn.ch.battalion.api.service.bean.BattalionSimpleBean;
import cn.ch.battalion.core.model.Battalion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by karma on 2018/4/15.
 */
public interface BattalionDao {
    List<BattalionBean> selects(PageRequest pageRequest);

    List<BattalionSimpleBean> selectSimples(PageRequest pageRequest);

    Integer selectTotalCount(PageRequest pageRequest);

    Integer selectCount(PageRequest pageRequest);

    void insert(Battalion battalion);

    void delete(Long id);

    void update(Battalion battalion);

    List<BattalionBean> selectByRank(@Param("id") Long id, @Param("limit") Integer limit, @Param("typeId") Long typeId);

    BattalionBean select(Long id);

    void addEnrollment(Long id);

    void subtractEnrollment(Long id);

    Long selectIdByName(String name);

    String selectName(Long id);
}
