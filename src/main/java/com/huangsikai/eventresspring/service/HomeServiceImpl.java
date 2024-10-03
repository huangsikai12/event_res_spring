package com.huangsikai.eventresspring.service;

import com.huangsikai.eventresspring.mapper.HomeGridMapper;
import com.huangsikai.eventresspring.pojo.HomeGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeGridMapper homeGridMapper;
    @Autowired
    RedisService redisService;
    @Override
    public List<HomeGrid> getHomeGridList() {
        return homeGridMapper.getHomeGridList();
    }

    @Override
    public String getTopInformation() {
        return redisService.get("top_information");
    }

    @Override
    public void setTopInformation(String value) {
         redisService.set("top_information",value);
    }
}
