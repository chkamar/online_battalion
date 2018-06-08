package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.service.FreeMakerService;
import cn.ch.battalion.core.model.Image;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by karma on 2018/5/17.
 */
@Service
public class FreeMakerServiceImpl implements FreeMakerService {
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public String getImageHtml(List<Image> images, String templateName) {
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            Map map=new HashMap();
            map.put("images",images);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
            return html;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
