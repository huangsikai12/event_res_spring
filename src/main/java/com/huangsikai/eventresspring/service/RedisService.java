package com.huangsikai.eventresspring.service;

import org.springframework.stereotype.Service;

/**
 * redis操作Service,
 * 对象和数组都以json形式进行存储
 * Created by macro on 2018/8/7.
 */
@Service
public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);
    void set(String key, String value,long time);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);

}
