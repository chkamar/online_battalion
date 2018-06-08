package cn.ch.battalion.api.base.controllor;

import cn.ch.battalion.api.base.result.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by karma on 2018/4/5.
 */
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResultData successResult() {
        return new ResultData(true, "操作成功");
    }

    public ResultData successResult(String url,Object object){
        return new ResultData(true,url,object);
    }

    public ResultData successResult(Object obj) {
        return new ResultData(true, obj, "操作成功");
    }

    public ResultData errorResult(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResultData(false, "操作失败");
    }

    public ResultData errorResult(Exception e, String msg) {
        logger.error(e.getMessage(), e);
        return new ResultData(false, msg);
    }

    public ResultData errorResult(String msg) {
        return new ResultData(false, msg);
    }

    public ResultData errorResult(String msg, Integer statusCode, String toUrl) {
        return new ResultData(false, msg, statusCode, toUrl);
    }

    public ResultData errorResult(String msg, Integer statusCode) {
        return new ResultData(false, msg, statusCode);
    }

}
