package cn.ch.battalion.core.dao;

import cn.ch.battalion.api.base.bean.PageRequest;
import cn.ch.battalion.core.model.Message;

import java.util.List;

/**
 * Created by karma on 2018/4/5.
 */
public interface MessageDao {

    List<Message> selects(PageRequest pageRequest);

    Integer selectTotalCount();

    void insert(Message message);

    void delete(Long id);
}
