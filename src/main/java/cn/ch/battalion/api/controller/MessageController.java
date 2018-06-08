package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.Global;
import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.ImageService;
import cn.ch.battalion.api.service.MessageService;
import cn.ch.battalion.api.service.bean.MessageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by karma on 2018/4/6.
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseWebController {
    @Resource
    private ImageService imageService;
    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model) {
        List<String> paths = imageService.getPathByType("news");
        model.addAttribute("paths", paths);
        return "message-edit";
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String adminHome(Model model) {
        logger.info("获取留言列表");
//        List<String> paths = imageService.getPathByType("news");
//        model.addAttribute("paths",paths);
        PageRequest pageRequest = new PageRequest(1, Global.MESSAGE_PAGE_SIZE, null);
        PageResponse pageResponse = messageService.getAllMessages(pageRequest);
            model.addAttribute("pageResponse", pageResponse);
        return "/admin/message-home";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/list", method = RequestMethod.POST)
    public ResultData<PageResponse> list(@RequestBody PageRequest pageRequest) {
        logger.info("获取分页留言");
        try {
            PageResponse<List<MessageBean>> pageResponse = messageService.getAllMessages(pageRequest);
            return successResult(pageResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "获取分页留言失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResultData add(@RequestBody String content, HttpServletRequest request) {
        logger.info("添加留言");
        try {
            messageService.addMessage(content, getLogin(request));
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "添加留言失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public ResultData delete(@PathVariable("id") Long id) {
        logger.info("删除留言");
        try {
            messageService.deleteMessage(id);
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "删除留言失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/reply", method = RequestMethod.GET)
    public ResultData reply(@RequestParam("id") Long id, @RequestParam("content") String content) {
        logger.info("回复留言");
        try {
            //TODO邮件服务

            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "回复留言失败");
        }
    }

}
