package cn.ch.battalion.api.base;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by karma on 2018/4/22.
 */
public class UploadConfig {
    @Value("${upload.imagePath}")
    private static String uploadImagePath;

    public static String getUploadImagePath() {
        return uploadImagePath;
    }

}
