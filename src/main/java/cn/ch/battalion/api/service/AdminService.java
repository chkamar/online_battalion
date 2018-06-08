package cn.ch.battalion.api.service;


import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.auth.Login;
import cn.ch.battalion.core.model.Admin;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by karma on 2018/4/12.
 */
public interface AdminService {
    ResultData login(Admin admin, HttpServletResponse response);

    void updatePwd(String password, Login login);
}
