package cn.ch.battalion.api.service;


import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.service.bean.Enrollment;

import java.util.List;

/**
 * Created by karma on 2018/4/12.
 */
public interface BattalionUserRelationService {
    PageResponse<List<Enrollment>> gets(PageRequest<Long> pageRequest);

    void add(Long battalionId, Long userId);

    void delete(Long id);

    Integer isEnrolled(Long battalionId, Long userId);

    void deleteByBattalionId(Long battalionId);

    Integer getCount(Long battalionId);

    void confirm(Long id);
}
