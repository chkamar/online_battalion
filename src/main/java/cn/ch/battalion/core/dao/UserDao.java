package cn.ch.battalion.core.dao;

import cn.ch.battalion.core.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by karma on 2018/4/6.
 */
public interface UserDao {

    String selectName(Long id);

    void insert(User user);

    Integer selectUserCountByName(String name);

    Integer selectUserCountByEmail(String email);

    Long selectId(User user);

    void updatePassword(@Param("id") Long id, @Param("password") String password);
}
