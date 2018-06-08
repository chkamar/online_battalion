package cn.ch.battalion.api.service;


import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.auth.Login;
import cn.ch.battalion.core.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by karma on 2018/4/6.
 */
public interface UserService {

    String getNameById(Long id);

    ResultData register(User user, HttpServletResponse response);

    ResultData login(User user, HttpServletResponse response);

    void updatePwd(String password, Login login);
}
