package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.AdminService;
import cn.ch.battalion.core.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by karma on 2018/4/15.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseWebController {

    @Resource
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultData login(HttpServletRequest request, @RequestBody Admin admin, HttpServletResponse response) {
        logger.info("管理员登录");
        return adminService.login(admin,response);
    }


    @RequestMapping(value = {"/home", ""}, method = RequestMethod.GET)
    public String index(HttpServletRequest request) {

        return "/admin/home";
    }

    @ResponseBody
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public ResultData updatePwd(@RequestBody Admin admin, HttpServletRequest request) {
        logger.info("管理员修改密码");
        try {
            adminService.updatePwd(admin.getPassword(), getLogin(request));
            return successResult("/admin",null);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e,"修改密码失败");
        }
    }

}
