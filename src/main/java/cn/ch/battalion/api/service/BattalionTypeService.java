package cn.ch.battalion.api.service;

import cn.ch.battalion.api.service.bean.BattalionTypeBean;
import cn.ch.battalion.core.model.BattalionType;

import java.util.List;

/**
 * Created by karma on 2018/5/4.
 */
public interface BattalionTypeService {
    List<BattalionTypeBean> getAll();
}
