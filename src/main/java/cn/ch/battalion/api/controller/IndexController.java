package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.Global;
import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.service.BattalionService;
import cn.ch.battalion.api.service.ImageService;
import cn.ch.battalion.api.service.NewsService;
import cn.ch.battalion.api.service.bean.BattalionBean;
import cn.ch.battalion.api.service.bean.NewsBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karma on 2018/5/4.
 */
@Controller
public class IndexController extends BaseWebController {
    @Resource
    private ImageService imageService;

    @Resource
    private NewsService newsService;

    @Resource
    private BattalionService battalionService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        //获取首页图片路径
        List<String> paths = imageService.getPathByType("index");
        model.addAttribute("paths",paths);
        //获取新闻top1-4
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageSize(4);
        pageRequest.setCurrentPage(1);

        List<NewsBean> news = newsService.getAllNews(pageRequest).getData();
        model.addAttribute("news", news);
        //获取训练营top1-8
        List<BattalionBean> battalions = battalionService.getByRank(null, Global.INDEX_RECOMMENDATION_COUNT, null);
        model.addAttribute("battalions", battalions);
//        pageRequest.setPageSize(6);
//        List<BattalionBean> battalions = (List)battalionService.getAllBattalion(pageRequest).getData();
//        model.addAttribute("battalions",battalions);

        return "index";
    }

}
