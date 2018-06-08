package cn.ch.battalion.api.service;


import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.service.bean.NewsBean;
import cn.ch.battalion.core.model.News;

import java.util.List;

/**
 * Created by karma on 2018/4/1.
 */
public interface NewsService {
    PageResponse<List<NewsBean>> getAllNews(PageRequest pageRequest);

    void updateReadCount(Long id);

//    void addNews(News news);

    void deleteNews(Long id);

//    void updateNews(News news);

    NewsBean getNews(Long id);

    News get(Long id);

    void upsert(News news);
}
