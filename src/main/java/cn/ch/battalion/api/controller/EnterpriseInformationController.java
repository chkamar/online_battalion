package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.EnterpriseInformationService;
import cn.ch.battalion.api.service.ImageService;
import cn.ch.battalion.core.model.EnterpriseInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by karma on 2018/4/15.
 */
@Controller
@RequestMapping("/enterpriseInformation")
public class EnterpriseInformationController extends BaseWebController {
    @Resource
    private ImageService imageService;
    @Resource
    private EnterpriseInformationService enterpriseInformationService;

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(Model model) {
        logger.info("获取企业信息");
        try {
            List<String> paths = imageService.getPathByType("enter_info");
            model.addAttribute("paths",paths);
            model.addAttribute("enterpriseInformation",enterpriseInformationService.get());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取企业信息失败");
            model.addAttribute("message","获取企业信息失败");
        }
        return "enterprise-information";
    }

    @RequestMapping(value = "/admin/edit",method = RequestMethod.GET)
    public String edit(Model model){
        logger.info("编辑企业信息");
        try {
            model.addAttribute("enterpriseInformation",enterpriseInformationService.get());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取企业信息失败");

        }
        return "admin/enterprise-information-edit";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/upsert", method = RequestMethod.POST)
    public ResultData upsert(@RequestBody EnterpriseInformation enterpriseInformation) {
        logger.info("添加/更新企业信息");
        try {
            enterpriseInformationService.upsert(enterpriseInformation);
            return successResult("/admin",null);
        } catch (Exception e) {
            e.printStackTrace();
            String message = "添加/更新企业信息失败";
            logger.error(message);
            return errorResult(message);
        }
    }

}
