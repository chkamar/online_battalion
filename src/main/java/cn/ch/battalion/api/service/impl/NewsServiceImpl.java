package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.service.NewsService;
import cn.ch.battalion.api.service.bean.NewsBean;
import cn.ch.battalion.core.dao.NewsDao;
import cn.ch.battalion.core.model.News;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karma on 2018/4/1.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsDao newsDao;

    @Override
    public PageResponse<List<NewsBean>> getAllNews(PageRequest pageRequest) {
        PageResponse<List<NewsBean>> pageResponse = new PageResponse<>();
        pageResponse.setCurrentPage(pageRequest.getCurrentPage());
        int pageSize = pageRequest.getPageSize();
        pageResponse.setPageSize(pageSize);
        int totalCount = newsDao.selectTotalCount();
        pageResponse.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        pageResponse.setTotalPage(totalPage);

        List<NewsBean> beans = new ArrayList<>();
        List<News> models = newsDao.selects(pageRequest);
        for (News model : models) {
            beans.add(this.getBean(model));
        }
        pageResponse.setData(beans);
        return pageResponse;
    }

    @Override
    public void updateReadCount(Long id) {
        newsDao.updateReadCount(id);
    }

//    @Override
//    public void addNews(News news) {
//        newsDao.insert(news);
//    }

    @Override
    public void deleteNews(Long id) {
        newsDao.delete(id);
    }

//    @Override
//    public void updateNews(News news) {
//        newsDao.update(news);
//    }

    @Override
    public NewsBean getNews(Long id) {
        NewsBean bean = this.getBean(newsDao.select(id));
        //如果成功查到该新闻，则浏览次数+1
        if (bean != null) {
            this.updateReadCount(id);
        }
        return bean;
    }

    private NewsBean getBean(News model) {
        if (model == null) {
            return null;
        }
        NewsBean bean = new NewsBean();
        BeanUtils.copyProperties(model, bean);
        String content = model.getContent();
        if (StringUtils.isNotEmpty(content)) {
            int end = content.length() >= NewsBean.SUMMARY_LENGTH ? NewsBean.SUMMARY_LENGTH : content.length();
            String summary = content.substring(0, end);
            summary = summary.replaceAll("</?[^>]+>", "").replaceAll("<a>\\s*|\t|\r|\n</a>", "");
            bean.setSummary(summary);
        }
        return bean;
    }

    @Override
    public News get(Long id) {
        return newsDao.select(id);
    }

    @Override
    public void upsert(News news) {
        news.setTime(new Date(System.currentTimeMillis()));
        if (news.getId() == null || news.getId() <= 0) {
            newsDao.insert(news);
        } else {
            newsDao.update(news);
        }
    }
}
