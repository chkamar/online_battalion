package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.service.EnterpriseInformationService;
import cn.ch.battalion.core.dao.EnterpriseInformationDao;
import cn.ch.battalion.core.model.EnterpriseInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by karma on 2018/4/12.
 */
@Service
public class EnterpriseInformationServiceImpl implements EnterpriseInformationService {

    @Resource
    private EnterpriseInformationDao enterpriseInformationDao;

    @Override
    public EnterpriseInformation get() {
        return enterpriseInformationDao.select();
    }

    @Override
    public void upsert(EnterpriseInformation enterpriseInformation) {
        if(enterpriseInformationDao.selectCount()==0){
            enterpriseInformationDao.insert(enterpriseInformation);
        }else {
            enterpriseInformationDao.update(enterpriseInformation);
        }
    }

}
