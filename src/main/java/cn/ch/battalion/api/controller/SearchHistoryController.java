package cn.ch.battalion.api.controller;

import cn.ch.battalion.api.base.controllor.BaseWebController;
import cn.ch.battalion.api.base.result.ResultData;
import cn.ch.battalion.api.service.SearchHistoryService;
import cn.ch.battalion.auth.Login;
import cn.ch.battalion.core.model.SearchHistory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Created by karma on 2018/5/8.
 */
@RestController
@RequestMapping("/searchHistory")
public class SearchHistoryController extends BaseWebController {

    @Resource
    private SearchHistoryService searchHistoryService;

    @RequestMapping(value = "/delete/{content}", method = RequestMethod.GET)
    public ResultData delete(@PathVariable("content") String content, HttpServletRequest request) {
        logger.info("删除搜索历史");
        Login login = getLogin(request);
        if(login!=null){
            try {
                searchHistoryService.delete(content);
                return successResult();
            } catch (Exception e) {
                e.printStackTrace();
                return errorResult(e, "删除搜索历史失败");
            }
        }
        return successResult();
    }

    @RequestMapping(value = "/upsert/{content}", method = RequestMethod.GET)
    public ResultData upsert(@PathVariable("content") String content, HttpServletRequest request) {
        logger.info("刷新搜索历史");
        Login login = getLogin(request);
        if(login!=null){
            try {
                SearchHistory searchHistory = new SearchHistory();
                searchHistory.setContent(content);
                searchHistory.setUserId(login.getUserId());
                searchHistory.setTime(new Date(System.currentTimeMillis()));
                searchHistory.setUserType(login.getType());

                searchHistoryService.upsert(searchHistory);
                return successResult();
            } catch (Exception e) {
                e.printStackTrace();
                return errorResult(e, "刷新搜索历史失败");
            }
        }
        return successResult();
    }

}
