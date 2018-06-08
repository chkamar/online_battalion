package cn.ch.battalion.core.dao;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.core.model.News;

import java.util.List;

/**
 * Created by karma on 2018/4/1.
 */
public interface NewsDao {

    List<News> selects(PageRequest<Object> pageRequest);

    void updateReadCount(Long id);

    void insert(News news);

    void delete(Long id);

    void update(News news);

    Integer selectTotalCount();

    News select(Long id);
}
