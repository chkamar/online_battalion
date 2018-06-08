package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.service.RecruitmentService;
import cn.ch.battalion.core.dao.RecruitmentDao;
import cn.ch.battalion.core.model.Recruitment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * Created by karma on 2018/5/16.
 */
@Service
public class RecruitmentServiceImpl implements RecruitmentService {
    @Resource
    private RecruitmentDao recruitmentDao;

    @Override
    public PageResponse<List<Recruitment>> gets(PageRequest pageRequest) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setCurrentPage(pageRequest.getCurrentPage());
        int pageSize = pageRequest.getPageSize();
        pageResponse.setPageSize(pageSize);
        int totalCount = recruitmentDao.selectCount(pageRequest);
        pageResponse.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        pageResponse.setTotalPage(totalPage);
        pageResponse.setData(recruitmentDao.selects(pageRequest));
        return pageResponse;
    }

    @Override
    public Recruitment get(Long id) {
        return recruitmentDao.select(id);
    }

    @Override
    public void delete(Long id) {
        recruitmentDao.delete(id);
    }

    @Override
    public void add(Recruitment recruitment) {
        recruitmentDao.insert(recruitment);
    }

    @Override
    public void update(Recruitment recruitment) {
        recruitmentDao.update(recruitment);
    }

    @Override
    public void upsert(Recruitment recruitment) {
        recruitment.setPublishTime(new Date(System.currentTimeMillis()));
        if (recruitment.getId() == null || recruitment.getId() <= 0) {
            recruitmentDao.insert(recruitment);
        } else {
            recruitmentDao.update(recruitment);
        }
    }
}
