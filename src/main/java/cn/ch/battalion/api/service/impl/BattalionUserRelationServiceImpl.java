package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.service.BattalionUserRelationService;
import cn.ch.battalion.api.service.bean.Enrollment;
import cn.ch.battalion.core.dao.BattalionDao;
import cn.ch.battalion.core.dao.BattalionUserRelationDao;
import cn.ch.battalion.core.model.BattalionUserRelation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * Created by karma on 2018/4/12.
 */
@Service
public class BattalionUserRelationServiceImpl implements BattalionUserRelationService {

    @Resource
    private BattalionUserRelationDao battalionUserRelationDao;
    @Resource
    private BattalionDao battalionDao;

    @Override
    public PageResponse<List<Enrollment>> gets(PageRequest<Long> pageRequest) {
        Long battalionId = pageRequest.getData();
        PageResponse<List<Enrollment>> pageResponse = new PageResponse<>();
        pageResponse.setCurrentPage(pageRequest.getCurrentPage());
        int pageSize = pageRequest.getPageSize();
        pageResponse.setPageSize(pageSize);
        int totalCount = battalionUserRelationDao.selectCount(battalionId);
        pageResponse.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        pageResponse.setTotalPage(totalPage);
        List<Enrollment> beans = battalionUserRelationDao.selectByBattalionId(battalionId);
        pageResponse.setData(beans);
        return pageResponse;
    }

    @Transactional
    @Override
    public void add(Long battalionId, Long userId) {
        BattalionUserRelation battalionUserRelation = new BattalionUserRelation();
        Date time = new Date(System.currentTimeMillis());
        battalionUserRelation.setUserId(userId);
        battalionUserRelation.setBattalionId(battalionId);
        battalionUserRelation.setTime(time);

        battalionUserRelationDao.insert(battalionUserRelation);
//        int i = 1/0;
        battalionDao.addEnrollment(battalionId);
    }

    @Transactional
    @Override
    public void delete(Long id) {
//        Date now = new Date(System.currentTimeMillis());
//        Date deadline = battalionUserRelationDao.selectDeadlineByRelationId(id);
//        if(now.before(deadline)) {
//            battalionUserRelationDao.deleteById(id);
//        }else{
//            //TODO 返回"已经过了截止日期，无法取消报名"
//        }
        Long battalionId = battalionUserRelationDao.selectBattalionId(id);
        battalionDao.subtractEnrollment(battalionId);
        battalionUserRelationDao.deleteById(id);
    }

    @Override
    public Integer getCount(Long battalionId) {
        return battalionUserRelationDao.selectCount(battalionId);
    }

    @Override
    public Integer isEnrolled(Long battalionId, Long userId) {
        return battalionUserRelationDao.selectRecord(battalionId, userId);
    }

    @Override
    public void deleteByBattalionId(Long battalionId) {
        battalionUserRelationDao.deleteByBattalionId(battalionId);
    }

    @Override
    public void confirm(Long id) {
        battalionUserRelationDao.updateStatus(id);
    }
}
