package cn.ch.battalion.api.service;

import cn.ch.battalion.core.model.Image;

import java.util.List;

/**
 * Created by karma on 2018/5/17.
 */
public interface FreeMakerService {
    String getImageHtml(List<Image> images, String template);
}
