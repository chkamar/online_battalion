package cn.ch.battalion.api.service;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.api.base.bean.PageResponse;
import cn.ch.battalion.api.service.bean.MessageBean;
import cn.ch.battalion.auth.Login;

import java.util.List;

/**
 * Created by karma on 2018/4/5.
 */
public interface MessageService {

    PageResponse<List<MessageBean>> getAllMessages(PageRequest pageRequest);

    void addMessage(String content, Login user);

    void deleteMessage(Long id);
}
