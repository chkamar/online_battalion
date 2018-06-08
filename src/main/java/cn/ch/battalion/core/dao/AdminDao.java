package cn.ch.battalion.core.dao;

import cn.ch.battalion.core.model.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * Created by karma on 2018/4/12.
 */
public interface AdminDao {

    Long selectId(Admin admin);

    void updatePassword(@Param("id") Long id, @Param("password") String password);

}
