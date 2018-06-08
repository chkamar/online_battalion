package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.service.BattalionTypeRelationService;
import cn.ch.battalion.core.dao.BattalionTypeRelationDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by karma on 2018/5/23.
 */
@Service
public class BattalionTypeRelationServiceImpl implements BattalionTypeRelationService {
    @Resource
    private BattalionTypeRelationDao battalionTypeRelationDao;

    @Override
    public void add(Long battalionId, Long typeId) {
        battalionTypeRelationDao.insert(battalionId,typeId);
    }

    @Override
    public void deleteByBattalionId(Long battalionId) {
        battalionTypeRelationDao.delete(battalionId);
    }
}
