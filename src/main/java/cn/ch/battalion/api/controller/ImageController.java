package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.FreeMakerService;
import cn.ch.battalion.api.service.ImageService;
import cn.ch.battalion.core.model.Image;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by karma on 2018/5/16.
 */
@Controller
@RequestMapping("/image")
public class ImageController extends BaseWebController {
    @Resource
    private ImageService imageService;

    @Resource
    private FreeMakerService freeMakerService;

    @ResponseBody
    @RequestMapping(value = "/admin/upload/{type}",method = RequestMethod.POST)
    public ResultData upload(@Param("file") MultipartFile file, @PathVariable("type") String type) {
        try {
            logger.info("上传图片");
            String path = imageService.upload(file, type);
            return successResult(path);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e, "上传失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/get/{type}")
    public ResultData getHtml(@PathVariable("type") String type) {
        logger.info("图片管理");
        try {
            List<Image> images = imageService.getByType(type);
            String html = freeMakerService.getImageHtml(images, "/admin/image-manage.ftl");
            return successResult(html);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e);
        }
    }

    @RequestMapping(value = "/admin/manage",method = RequestMethod.GET)
    public String manage(Model model){
        List<Image> images = imageService.getByType("index");
        model.addAttribute("images",images);
        return "admin/image-manage";
    }

    @ResponseBody
    @RequestMapping(value = "/admin/get",method = RequestMethod.GET)
    public ResultData get(){
        logger.info("首页图片列表");
        try {
            List<Image> images = imageService.getByType("index");
            return successResult(images);
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/admin/delete/{id}")
    public ResultData delete(@PathVariable("id") Long id) {
        logger.info("删除图片");
        try {
            imageService.delete(id);
            return successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return errorResult(e);
        }
    }

}
