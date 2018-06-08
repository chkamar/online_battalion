package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.service.BattalionTypeService;
import cn.ch.battalion.api.service.bean.BattalionTypeBean;
import cn.ch.battalion.core.dao.BattalionTypeDao;
import cn.ch.battalion.core.model.BattalionType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by karma on 2018/5/4.
 */
@Service
public class BattalionTypeServiceImpl implements BattalionTypeService {

    @Resource
    private BattalionTypeDao battalionTypeDao;

    @Override
    public List<BattalionTypeBean> getAll() {
        return battalionTypeDao.selects();
    }
}
