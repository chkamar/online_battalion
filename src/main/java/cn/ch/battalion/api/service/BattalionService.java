package cn.ch.battalion.api.service;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.service.bean.BattalionBean;
import cn.ch.battalion.api.service.bean.BattalionSearchBean;
import cn.ch.battalion.api.service.bean.BattalionSimpleBean;
import cn.ch.battalion.core.model.Battalion;

import java.util.List;

/**
 * Created by karma on 2018/4/15.
 */
public interface BattalionService {
    PageResponse<List<BattalionBean>> getAllBattalion(PageRequest<BattalionSearchBean> pageRequest);

    PageResponse<List<BattalionSimpleBean>> gets(PageRequest<BattalionSearchBean> pageRequest);

    void add(Battalion battalion);

    void delete(Long id);

    void update(Battalion battalion);

    List<BattalionBean> getByRank(Long id, Integer limit, Long typeId);

    BattalionBean get(Long id);

    void updateEnrollment(Long id);

    Long getId(String name);

    void upsert(BattalionBean battalionBean);

    String getName(Long id);
}
