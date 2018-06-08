package cn.ch.battalion.api.service.impl;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.service.MessageService;
import cn.ch.battalion.api.service.bean.MessageBean;
import cn.ch.battalion.auth.Login;
import cn.ch.battalion.core.dao.MessageDao;
import cn.ch.battalion.core.dao.UserDao;
import cn.ch.battalion.core.model.Message;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karma on 2018/4/5.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageDao messageDao;
    @Resource
    private UserDao userDao;

    @Override
    public PageResponse<List<MessageBean>> getAllMessages(PageRequest pageRequest) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setCurrentPage(pageRequest.getCurrentPage());
        int pageSize = pageRequest.getPageSize();
        pageResponse.setPageSize(pageSize);
        int totalCount = messageDao.selectTotalCount();
        pageResponse.setTotalCount(totalCount);
        int totalPage = 0;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        pageResponse.setTotalPage(totalPage);

        List<Message> models = messageDao.selects(pageRequest);
        List<MessageBean> beans = new ArrayList<>();
        for (Message model : models) {
            MessageBean bean = new MessageBean();
            BeanUtils.copyProperties(model, bean);
            Long userId = model.getUserId();
            String userName = userDao.selectName(userId);
            bean.setUserName(userName);
            beans.add(bean);
        }
        pageResponse.setData(beans);
        return pageResponse;
    }

    @Override
    public void addMessage(String content, Login user) {
        if (StringUtils.isNotEmpty(content)) {
            Message message = new Message();
            message.setContent(content);
            Date time = new Date(System.currentTimeMillis());
            message.setTime(time);
            message.setUserId(user.getUserId());
            messageDao.insert(message);
        }
    }

    @Override
    public void deleteMessage(Long id) {
        messageDao.delete(id);
    }
}
