package com.huangsikai.eventresspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * redis操作Service的实现类
 * Created by macro on 2018/8/7.
 */
@Service
public class RedisServiceImpl implements RedisService {

    //注入RedisTemplate类，SpringBoot自动将其注入到了容器中了
    @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
    @Override
    public void set(String key, String value,long time) {
        stringRedisTemplate.opsForValue().set(key, value,time,TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value == null)
            return "null";
        return value ;
    }

    @Override
    public boolean expire(String key, long expire) {
        return Boolean.TRUE.equals(stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS));
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key,delta);
    }
}
