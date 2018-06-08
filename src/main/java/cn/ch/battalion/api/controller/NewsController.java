package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.Global;
import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.ImageService;
import cn.ch.battalion.api.service.NewsService;
import cn.ch.battalion.api.service.bean.NewsBean;
import cn.ch.battalion.core.model.News;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by karma on 2018/4/1.
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseWebController {
    @Resource
    private ImageService imageService;
    @Resource
    private NewsService newsService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        logger.info("前往新闻中心");
        List<String> paths = imageService.getPathByType("news");
        model.addAttribute("paths", paths);
        PageRequest pageRequest = new PageRequest(1, Global.NEWS_PAGE_SIZE, null);
        PageResponse pageResponse = newsService.getAllNews(pageRequest);
        model.addAttribute("pageResponse", pageResponse);
        return "news-home";
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String adminHome(Model model) {
        logger.info("前往新闻管理中心");
        PageRequest pageRequest = new PageRequest(1, Global.ADMIN_NEWS_PAGE_SIZE, null);
        PageResponse pageResponse = newsService.getAllNews(pageRequest);
        model.addAttribute("pageResponse", pageResponse);
        return "/admin/news-home";
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        News news = null;
        if (id != null && id > 0) {
            news = newsService.get(id);
        } else {
            news = new News();
        }
        model.addAttribute("news", news);
        return "/admin/news-edit";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/upsert", method = RequestMethod.POST)
    public ResultData upsert(HttpServletRequest request, @RequestBody News news) {
        logger.info("添加/修改训练营");
        try {
            newsService.upsert(news);
            return successResult("/news/admin/home", null);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "添加/修改失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultData list(@RequestBody PageRequest pageRequest) {
        logger.info("获取新闻列表");
        try {
            PageResponse<List<NewsBean>> pageResponse = newsService.getAllNews(pageRequest);
            return successResult(pageResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "获取新闻列表失败");
        }
    }

//    @ResponseBody
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public ResultData add(@RequestParam(value = "news") News news) {
//        logger.info("添加新闻");
//        try {
//            newsService.addNews(news);
//            return successResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return errorResult(e, "添加新闻失败");
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public ResultData delete(@PathVariable("id") Long id) {
        logger.info("删除新闻");
        try {
            newsService.deleteNews(id);
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "删除新闻失败");
        }
    }

//    @ResponseBody
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public ResultData update(@RequestParam(value = "news") News news) {
//        logger.info("更新新闻");
//        try {
//            newsService.updateNews(news);
//            return successResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return errorResult(e, "更新新闻失败");
//        }
//    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String details(@PathVariable("id") Long id, Model model) {
        logger.info("获取新闻细节：" + id);
        try {
            List<String> paths = imageService.getPathByType("news");
            model.addAttribute("paths", paths);
            model.addAttribute("news", newsService.getNews(id));
        } catch (Exception e) {
            e.printStackTrace();
            String message = "获取新闻细节失败";
            model.addAttribute("message", message);
            logger.error(message);
        }
        return "news-details";
    }

}
