package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.Global;
import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.BattalionService;
import cn.ch.battalion.api.service.BattalionUserRelationService;
import cn.ch.battalion.api.service.bean.EnrollBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by karma on 2018/4/15.
 */
@Controller
@RequestMapping("/battalionUserRelation")
public class BattalionUserRelationController extends BaseWebController {
    @Resource
    private BattalionUserRelationService battalionUserRelationService;

    @RequestMapping(value = "/admin/details/{battalionId}")
    public String detail(@PathVariable("battalionId") Long battalionId, Model model) {
        logger.info("获取id为" + battalionId + "的训练营的报名信息");
        PageRequest<Long> pageRequest = new PageRequest(1, Global.ENROLLMENT_PAGE_SIZE, battalionId);
        model.addAttribute("pageResponse", battalionUserRelationService.gets(pageRequest));
        return "/admin/enroll-info";
    }

    @ResponseBody
    @RequestMapping(value = "/user/add/{battalionId}", method = RequestMethod.GET)
    public ResultData add(HttpServletRequest request, @PathVariable("battalionId") Long battalionId) {
        logger.info("用户报名训练营");
        try {
            Long userId = getLogin(request).getUserId();
            battalionUserRelationService.add(battalionId, userId);
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "报名失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public ResultData delete(HttpServletRequest request, @PathVariable("id") Long id) {
        logger.info("取消报名");
        try {
            battalionUserRelationService.delete(id);
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "取消失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/confirm/{id}", method = RequestMethod.GET)
    public ResultData confirm(HttpServletRequest request, @PathVariable("id") Long id) {
        logger.info("确认报名");
        try {
            battalionUserRelationService.confirm(id);
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "确认失败");
        }
    }

}
