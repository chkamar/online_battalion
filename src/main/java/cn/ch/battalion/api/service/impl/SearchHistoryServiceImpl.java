package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.service.SearchHistoryService;
import cn.ch.battalion.api.service.bean.HistoryBean;
import cn.ch.battalion.core.dao.SearchHistoryDao;
import cn.ch.battalion.core.model.SearchHistory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * Created by karma on 2018/5/4.
 */
@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Resource
    private SearchHistoryDao searchHistoryDao;

    @Override
    public void delete(String content) {
        searchHistoryDao.delete(content);
    }

    @Override
    public void delete(Long id) {
        searchHistoryDao.deleteById(id);
    }

//    @Override
//    public List<HistoryBean> get(Long userId, int limit) {
//        return searchHistoryDao.selects(userId, limit);
//    }

    @Override
    public List<String> get(Long userId, String userType, int limit) {
        return searchHistoryDao.selects(userId, userType, limit);
    }

    @Override
    public void upsert(SearchHistory searchHistory) {
        Long id = searchHistoryDao.selectId(searchHistory.getUserId(), searchHistory.getContent());
        Date time = new Date(System.currentTimeMillis());
        if (id != null && id > 0) {
            searchHistoryDao.update(time, id);
        } else {
            searchHistory.setTime(time);
            if (searchHistoryDao.selectCount() == 10) {
                searchHistoryDao.deleteOld();
            }
            searchHistoryDao.insert(searchHistory);
        }
    }

}
