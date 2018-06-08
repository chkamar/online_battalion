package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.controllor.BaseWebController;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenhang1 on 2018/4/13.
 */
@RestController
@RequestMapping("/upload")
public class UploadController extends BaseWebController {

    @Value("${upload.imagePath}")
    private String uploadImagePath;

    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public String uploadImage(HttpServletRequest request,
                              @Param("file")MultipartFile file) throws Exception{
        logger.info("upload image...");
        String title = null;
        String fileName = null;
        Map<String,Object> jsonMap = new HashMap<>();
        try {
            //服务器路径
//            String path = request.getServletContext().getRealPath("/image/"+from);
//            String path = "\\document\\idea-workspace\\online_battalion\\src\\main\\webapp\\static\\image";
            String oriName = file.getOriginalFilename();
            int pointIndex = oriName.lastIndexOf('.');
            title = oriName.substring(0,pointIndex);
            fileName = title+System.currentTimeMillis()+oriName.substring(pointIndex);

            File imageFile = new File(uploadImagePath,fileName);
            if(!imageFile.getParentFile().exists()){
                imageFile.getParentFile().mkdirs();
            }
            file.transferTo(imageFile);
            java.lang.Thread.sleep(1000);
            jsonMap.put("code",0);
            jsonMap.put("msg","success");
            Map<String,Object> data = new HashMap<>();
            data.put("src","/upload/image/"+fileName);
            data.put("title",title);
            jsonMap.put("data",data);
        } catch (IOException e) {
            e.printStackTrace();
            jsonMap.put("code",-1);
            jsonMap.put("msg","fail");
        }
        String jsonString = new JSONObject(jsonMap).toJSONString();
        return jsonString;
    }


}
