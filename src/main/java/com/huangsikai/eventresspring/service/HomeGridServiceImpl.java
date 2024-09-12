package com.huangsikai.eventresspring.service;

import com.huangsikai.eventresspring.mapper.HomeGridMapper;
import com.huangsikai.eventresspring.pojo.HomeGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HomeGridServiceImpl implements HomeGridService {

    @Autowired
    HomeGridMapper homeGridMapper;
    @Override
    public List<HomeGrid> getHomeGridList() {
        return homeGridMapper.getHomeGridList();
    }
}
