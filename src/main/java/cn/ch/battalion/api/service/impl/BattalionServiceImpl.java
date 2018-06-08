package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.base.CalendarUtil;
import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.service.BattalionService;
import cn.ch.battalion.api.service.BattalionTypeRelationService;
import cn.ch.battalion.api.service.BattalionUserRelationService;
import cn.ch.battalion.api.service.SearchHistoryService;
import cn.ch.battalion.api.service.bean.BattalionBean;
import cn.ch.battalion.api.service.bean.BattalionSearchBean;
import cn.ch.battalion.api.service.bean.BattalionSimpleBean;
import cn.ch.battalion.core.dao.BattalionDao;
import cn.ch.battalion.core.model.Battalion;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by karma on 2018/4/15.
 */
@Service
public class BattalionServiceImpl implements BattalionService {

    @Resource
    private BattalionDao battalionDao;

    @Resource
    private BattalionUserRelationService battalionUserRelationService;

    @Resource
    private BattalionTypeRelationService battalionTypeRelationService;

    @Override
    public PageResponse<List<BattalionBean>> getAllBattalion(PageRequest<BattalionSearchBean> pageRequest) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setCurrentPage(pageRequest.getCurrentPage());
        int pageSize = pageRequest.getPageSize();
        pageResponse.setPageSize(pageSize);
        int totalCount = battalionDao.selectTotalCount(pageRequest);
        pageResponse.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        pageResponse.setTotalPage(totalPage);
        pageResponse.setData(battalionDao.selects(pageRequest));
        return pageResponse;
    }

    @Override
    public void add(Battalion battalion) {
        battalionDao.insert(battalion);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        battalionDao.delete(id);
        battalionTypeRelationService.deleteByBattalionId(id);
        battalionUserRelationService.deleteByBattalionId(id);
    }

    @Override
    public void update(Battalion battalion) {
        battalionDao.update(battalion);
    }

    @Override
    public List<BattalionBean> getByRank(Long id, Integer limit, Long typeId) {
        return battalionDao.selectByRank(id,limit, typeId);
    }

    @Override
    public BattalionBean get(Long id) {
        return battalionDao.select(id);
    }

    @Override
    public void updateEnrollment(Long id) {
        battalionDao.addEnrollment(id);
    }

    @Override
    public Long getId(String name) {
        return battalionDao.selectIdByName(name);
    }

    @Transactional
    @Override
    public void upsert(BattalionBean battalionBean) {
        Battalion battalion = new Battalion();
        BeanUtils.copyProperties(battalionBean, battalion);
        int duration = CalendarUtil.differentDays(battalion.getTimeStart(),battalion.getTimeEnd());
        battalion.setDuration(duration);
        if (battalionBean.getId() == null || battalionBean.getId() <= 0) {
            battalionDao.insert(battalion);
            Long battalionId = battalionDao.selectIdByName(battalionBean.getName());
            battalionTypeRelationService.add(battalionId, battalionBean.getTypeId());
        } else {
            battalionDao.update(battalion);
            battalionTypeRelationService.add(battalionBean.getId(), battalionBean.getTypeId());
        }

    }

    @Override
    public PageResponse<List<BattalionSimpleBean>> gets(PageRequest<BattalionSearchBean> pageRequest) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setCurrentPage(pageRequest.getCurrentPage());
        int pageSize = pageRequest.getPageSize();
        pageResponse.setPageSize(pageSize);
        int totalCount = battalionDao.selectCount(pageRequest);
        pageResponse.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        pageResponse.setTotalPage(totalPage);
        pageResponse.setData(battalionDao.selectSimples(pageRequest));
        return pageResponse;
    }

    @Override
    public String getName(Long id) {
        return battalionDao.selectName(id);
    }
}
