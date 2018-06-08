package cn.ch.battalion.api.service;

import cn.ch.battalion.core.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by karma on 2018/4/22.
 */
public interface ImageService {

    List<Image> getByType(String type);

    List<String> getPathByType(String type);

    String upload(MultipartFile file,String type);

    void delete(Long id);

//    public void manage(){
//        String imageUploadPath = UploadConfig.getUploadImagePath();
    //1获取image文件夹的所有图片文件；

    //2获取数据表（新闻、企业信息表）的所有图片路径；TODO

    //3删除1-2的所有图片。
//    }
}
