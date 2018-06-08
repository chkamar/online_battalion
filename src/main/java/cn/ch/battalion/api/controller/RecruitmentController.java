package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.Global;
import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.ImageService;
import cn.ch.battalion.api.service.RecruitmentService;
import cn.ch.battalion.core.model.Recruitment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by karma on 2018/5/16.
 */
@Controller
@RequestMapping("/recruitment")
public class RecruitmentController extends BaseWebController {
    @Resource
    private ImageService imageService;
    @Resource
    private RecruitmentService recruitmentService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        logger.info("初始招聘中心");
        List<String> paths = imageService.getPathByType("recruitment");
        model.addAttribute("paths",paths);
        PageRequest pageRequest = new PageRequest(1, Global.RECRUITMENT_PAGE_SIZE, null);
        model.addAttribute("pageResponse", recruitmentService.gets(pageRequest));
        return "recruitment-home";
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String adminHome(Model model) {
        logger.info("初始招聘管理中心");
        PageRequest pageRequest = new PageRequest(1, Global.RECRUITMENT_PAGE_SIZE, null);
        model.addAttribute("pageResponse", recruitmentService.gets(pageRequest));
        return "admin/recruitment-home";
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        Recruitment recruitment = null;
        if (id != null && id > 0) {
            recruitment = recruitmentService.get(id);
        } else {
            recruitment = new Recruitment();
        }
        model.addAttribute("recruitment", recruitment);
        return "/admin/recruitment-edit";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/upsert", method = RequestMethod.POST)
    public ResultData upsert(HttpServletRequest request, @RequestBody Recruitment recruitment) {
        logger.info("添加/修改招聘信息");
        try {
            recruitmentService.upsert(recruitment);
            return successResult("/recruitment/admin/home", null);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "添加/修改失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public ResultData<PageResponse> list(@RequestBody PageRequest pageRequest){
        logger.info("获取招聘信息列表");
        try {
            PageResponse pageResponse = recruitmentService.gets(pageRequest);
            return successResult(pageResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e,"获取招聘列表失败");
        }
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Long id, Model model) {
        List<String> paths = imageService.getPathByType("recruitment");
        model.addAttribute("paths",paths);
        Recruitment recruitment = recruitmentService.get(id);
        model.addAttribute("recruitment",recruitment);
        return "recruitment-details";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public ResultData delete(@PathVariable("id") Long id) {
        logger.info("删除招聘信息");
        try {
            recruitmentService.delete(id);
             return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e,"删除招聘信息失败");
        }
    }

//    @ResponseBody
//    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
//    public ResultData add(@RequestBody Recruitment recruitment) {
//        logger.info("添加招聘信息");
//        try {
//            recruitmentService.add(recruitment);
//            return successResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return errorResult(e,"添加招聘信息失败");
//        }
//    }

//    @ResponseBody
//    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
//    public ResultData update(@RequestBody Recruitment recruitment) {
//        logger.info("更新招聘信息");
//        try {
//            recruitmentService.update(recruitment);
//            return successResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return errorResult(e,"更新招聘信息失败");
//        }
//    }
}
