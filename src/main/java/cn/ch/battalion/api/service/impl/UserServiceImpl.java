package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.UserService;
import cn.ch.battalion.auth.CookieUtils;
import cn.ch.battalion.auth.JWT;
import cn.ch.battalion.auth.Login;
import cn.ch.battalion.core.dao.UserDao;
import cn.ch.battalion.core.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by karma on 2018/4/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private CookieUtils cookieUtils;

    @Override
    public String getNameById(Long id) {
        return userDao.selectName(id);
    }

    @Override
    public ResultData register(User user, HttpServletResponse response) {
        //同名检测
        if (this.hasSameName(user.getName())) {
            return new ResultData(false, "用户名重复");
        }
        //同email检测
        if (this.hasSameEmail(user.getEmail())) {
            return new ResultData(false, "该email已经被注册了");
        }
        //前端密码加密
//        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
//        user.setPassword(password);
        userDao.insert(user);

        //省略登录步骤，直接跳转到首页。
        try {
            Login login = this.getLoginUser(user);
            String token = JWT.creatToken(login);
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("login", login);

            cookieUtils.setCookie(response,"token",token);

            return new ResultData(true, "注册成功", data, 0, "/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(false, "注册失败");
        }
    }

    @Override
    public ResultData login(User user, HttpServletResponse response) {
        Login login = this.getLoginUser(user);
        if (login == null) {
            return new ResultData(false, "用户名或密码错误");
        }
        //验证成功，生成token
        try {
            String token = JWT.creatToken(login);

//            Cookie cookie = new Cookie("token", token);
////        cookie.setMaxAge(3600);
//            cookie.setDomain("127.0.0.1");
//            cookie.setPath("/");
//            cookie.setSecure(true);
//            response.addCookie(cookie);
            cookieUtils.setCookie(response,"token",token);

            return new ResultData(true, "登录成功", null, 0, "/index");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(false, "登录失败");
        }
    }

    @Override
    public void updatePwd(String password, Login login) {
        Long userId = login.getUserId();
//        try {
            userDao.updatePassword(userId,password);
//            return new ResultData(true, "修改密码成功", null, 0, "/index");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResultData(false,"修改密码失败");
//        }
    }

    private Login getLoginUser(User user) {
        Login login = null;
        Long id = userDao.selectId(user);
        if (id != null) {
            login = new Login();
            login.setUserId(id);
            login.setUserName(user.getName());
            login.setType("user");
        }

        return login;
    }

    private Boolean hasSameName(String name) {
        return userDao.selectUserCountByName(name) > 0 ? true : false;
    }

    private Boolean hasSameEmail(String email) {
        return userDao.selectUserCountByEmail(email) > 0 ? true : false;
    }
}
