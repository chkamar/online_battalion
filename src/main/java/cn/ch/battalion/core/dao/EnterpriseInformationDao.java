package cn.ch.battalion.core.dao;


import cn.ch.battalion.core.model.EnterpriseInformation;

/**
 * Created by karma on 2018/4/12.
 */
public interface EnterpriseInformationDao {
    EnterpriseInformation select();

    Integer selectCount();

    void insert(EnterpriseInformation enterpriseInformation);

    void update(EnterpriseInformation enterpriseInformation);
}
