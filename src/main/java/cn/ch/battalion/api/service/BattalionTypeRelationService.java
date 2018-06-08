package cn.ch.battalion.api.service;

/**
 * Created by karma on 2018/5/23.
 */
public interface BattalionTypeRelationService {
    void add(Long battalionId, Long typeId);

    void deleteByBattalionId(Long battalionId);
}
