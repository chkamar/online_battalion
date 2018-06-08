package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.Global;
import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.*;
import cn.ch.battalion.api.service.bean.BattalionBean;
import cn.ch.battalion.api.service.bean.BattalionSearchBean;
import cn.ch.battalion.api.service.bean.BattalionTypeBean;
import cn.ch.battalion.auth.Login;
import cn.ch.battalion.core.model.Battalion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by karma on 2018/4/15.
 */
@Controller
@RequestMapping("/battalion")
public class BattalionController extends BaseWebController {
    @Resource
    private ImageService imageService;
    @Resource
    private BattalionService battalionService;
    @Resource
    private BattalionTypeService battalionTypeService;
    @Resource
    private BattalionUserRelationService battalionUserRelationService;
    @Resource
    private SearchHistoryService searchHistoryService;
    @Resource
    private BattalionTypeRelationService battalionTypeRelationService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        logger.info("初始化训练营中心");
        List<String> paths = imageService.getPathByType("index");
        model.addAttribute("paths", paths);
        //获取训练营类型集合
        List<BattalionTypeBean> types = battalionTypeService.getAll();
        model.addAttribute("types", types);
        //获取搜索记录
//        List<HistoryBean> histories = searchHistoryService.get(getLogin(request).getUserId(), 10);
        Login login = getLogin(request);
        if (login != null) {
            List<String> histories = searchHistoryService.get(login.getUserId(), login.getType(), Global.SEARCH_HISTORY_COUNT);
            model.addAttribute("histories", histories);
        }
        //获取初始的训练营列表
        PageRequest pageRequest = new PageRequest(1, Global.BATTALION_PAGE_SIZE, null);
        PageResponse pageResponse = battalionService.getAllBattalion(pageRequest);
        model.addAttribute("pageResponse", pageResponse);

        return "battalion-home";
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String adminHome(Model model, HttpServletRequest request) {
        logger.info("初始化训练营管理中心");
        //获取训练营类型集合
        List<BattalionTypeBean> types = battalionTypeService.getAll();
        model.addAttribute("types", types);
        //获取搜索记录
//        List<HistoryBean> histories = searchHistoryService.get(getLogin(request).getUserId(), 10);
        Login login = getLogin(request);
        if (login != null) {
            List<String> histories = searchHistoryService.get(login.getUserId(), login.getType(), Global.SEARCH_HISTORY_COUNT);
            model.addAttribute("histories", histories);
        }
        //获取初始的训练营列表
        PageRequest pageRequest = new PageRequest(1, Global.BATTALION_PAGE_SIZE, null);
        PageResponse pageResponse = battalionService.gets(pageRequest);
        model.addAttribute("pageResponse", pageResponse);

        return "/admin/battalion-home";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultData list(@RequestBody PageRequest<BattalionSearchBean> pageRequest) {
        logger.info("获取训练营列表");
        try {
            PageResponse pageResponse = battalionService.getAllBattalion(pageRequest);
            return successResult(pageResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "获取训练营列表失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/list", method = RequestMethod.POST)
    public ResultData adminList(@RequestBody PageRequest<BattalionSearchBean> pageRequest) {
        logger.info("获取训练营列表");
        try {
            PageResponse pageResponse = battalionService.gets(pageRequest);
            return successResult(pageResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "获取训练营列表失败");
        }
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        logger.info("获取训练营详情");
        List<String> paths = imageService.getPathByType("battalion");
        model.addAttribute("paths", paths);
        BattalionBean battalion = battalionService.get(id);
        model.addAttribute("battalion", battalion);
        Login login = getLogin(request);

        if (login != null) {
            Integer enrollStatus = battalionUserRelationService.isEnrolled(id, login.getUserId());
            model.addAttribute("enrollStatus", enrollStatus);
        }
        List<BattalionBean> recommendations = battalionService.getByRank(id, Global.DETAILS_RECOMMENDATION_COUNT, battalion.getTypeId());
        model.addAttribute("recommendations", recommendations);
        return "battalion-details";
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        BattalionBean battalion = new BattalionBean();
        if (id != null && id > 0) {
            battalion = battalionService.get(id);
        }
        model.addAttribute("battalion", battalion);
        //获取所有类型
        List<BattalionTypeBean> types = battalionTypeService.getAll();
        model.addAttribute("types", types);
        return "/admin/battalion-edit";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/upsert", method = RequestMethod.POST)
    public ResultData upsert(HttpServletRequest request, @RequestBody BattalionBean battalionBean) {
        logger.info("添加/修改训练营");
        try {
            battalionService.upsert(battalionBean);
            return successResult("/battalion/admin/home", null);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "添加/修改失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public ResultData add(HttpServletRequest request, @RequestBody Battalion battalion) {
        logger.info("添加训练营");
        try {
            battalionService.add(battalion);
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "添加失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public ResultData delete(HttpServletRequest request, @PathVariable("id") Long id) {
        logger.info("删除训练营");
        try {
            battalionService.delete(id);
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "删除失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public ResultData update(HttpServletRequest request, @RequestParam("battalion") Battalion battalion) {
        logger.info("更新训练营信息");
        try {
            battalionService.update(battalion);
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "更新失败");
        }
    }


}
