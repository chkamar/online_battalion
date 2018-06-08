package cn.ch.battalion.api.service;

import cn.ch.battalion.core.model.EnterpriseInformation;

/**
 * Created by karma on 2018/4/12.
 */
public interface EnterpriseInformationService {
    EnterpriseInformation get();

    void upsert(EnterpriseInformation enterpriseInformation);
}
