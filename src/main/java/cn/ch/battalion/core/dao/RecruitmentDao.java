package cn.ch.battalion.core.dao;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.core.model.Recruitment;

import java.util.List;

/**
 * Created by karma on 2018/5/16.
 */
public interface RecruitmentDao {
    List<Recruitment> selects(PageRequest pageRequest);

    Integer selectCount(PageRequest pageRequest);

    Recruitment select(Long id);

    void delete(Long id);

    void insert(Recruitment recruitment);

    void update(Recruitment recruitment);
}
