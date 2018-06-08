package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.UserService;
import cn.ch.battalion.core.model.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by karma on 2018/4/6.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseWebController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultData register(@RequestBody User user, HttpServletResponse response) {
        logger.info("用户注册");
        return userService.register(user, response);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultData login(@RequestBody User user, HttpServletResponse response) {
        logger.info("用户登录");
        return userService.login(user, response);
    }

    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public ResultData updatePwd(@RequestBody User user, HttpServletRequest request) {
        logger.info("用户修改密码");
        try {
            userService.updatePwd(user.getPassword(), getLogin(request));
            return successResult("/index",null);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e,"修改密码失败");
        }
    }
}
