package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.service.ImageService;
import cn.ch.battalion.core.dao.ImageDao;
import cn.ch.battalion.core.model.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by karma on 2018/5/16.
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Value("${upload.imagePath}")
    private String uploadImagePath;

    @Resource
    private ImageDao imageDao;

    @Override
    public List<String> getPathByType(String type) {
        return imageDao.selectPathByType(type);
    }

    @Override
    public List<Image> getByType(String type) {
        return imageDao.selectByType(type);
    }

    @Override
    public String upload(MultipartFile file, String type) {
        try {
            String oriName = file.getOriginalFilename();
            int pointIndex = oriName.lastIndexOf('.');
            String title = oriName.substring(0, pointIndex);
            String fileName = title + System.currentTimeMillis() + oriName.substring(pointIndex);

            File imageFile = null;
            if (!StringUtils.isEmpty(type)) {
                imageFile = new File(uploadImagePath + type + "/", fileName);
            } else {
                imageFile = new File(uploadImagePath, fileName);
            }
            if (!imageFile.getParentFile().exists()) {
                imageFile.getParentFile().mkdirs();
            }
            file.transferTo(imageFile);
            String path = "/upload/image/" + type + "/" + fileName;
            imageDao.insert(path, type);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        imageDao.delete(id);
    }
}
