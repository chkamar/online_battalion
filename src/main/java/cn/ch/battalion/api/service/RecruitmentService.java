package cn.ch.battalion.api.service;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.core.model.Recruitment;

import java.util.List;

/**
 * Created by karma on 2018/5/16.
 */
public interface RecruitmentService {
    PageResponse<List<Recruitment>> gets(PageRequest pageRequest);

    Recruitment get(Long id);

    void delete(Long id);

    void add(Recruitment recruitment);

    void update(Recruitment recruitment);

    void upsert(Recruitment recruitment);
}
