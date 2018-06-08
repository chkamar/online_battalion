package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.AdminService;
import cn.ch.battalion.auth.CookieUtils;
import cn.ch.battalion.auth.JWT;
import cn.ch.battalion.auth.Login;
import cn.ch.battalion.core.dao.AdminDao;
import cn.ch.battalion.core.model.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by karma on 2018/4/12.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Resource
    private CookieUtils cookieUtils;

    @Override
    public ResultData login(Admin admin, HttpServletResponse response) {
        Login login = this.getLoginAdmin(admin);
        if (login == null) {
            return new ResultData(false, "管理员名称或密码错误");
        }
        try {
            //验证成功，生成token
            String token = JWT.creatToken(login);

//            Cookie cookie = new Cookie("token", token);
////        cookie.setMaxAge(3600);
//            cookie.setDomain("127.0.0.1");
//            cookie.setPath("/");
//            cookie.setSecure(true);
//            response.addCookie(cookie);
            cookieUtils.setCookie(response,"token",token);

            return new ResultData(true, "登录成功", null, 0, "/admin");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(false, "登录失败");
        }
    }

    @Override
    public void updatePwd(String password, Login login) {
        Long adminId = login.getUserId();
        adminDao.updatePassword(adminId,password);
    }

    private Login getLoginAdmin(Admin admin){
        Login login = null;
        Long id = adminDao.selectId(admin);
        if (id != null) {
            login = new Login();
            login.setUserId(id);
            login.setUserName(admin.getName());
            login.setType("admin");
        }

        return login;
    }
}
