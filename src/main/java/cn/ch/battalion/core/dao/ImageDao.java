package cn.ch.battalion.core.dao;

import cn.ch.battalion.core.model.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by karma on 2018/5/16.
 */
public interface ImageDao {
    List<Image> selectByType(String type);

    List<String> selectPathByType(String type);

    void insert(@Param("path")String path,@Param("type")String typr);

    void delete(Long id);
}
