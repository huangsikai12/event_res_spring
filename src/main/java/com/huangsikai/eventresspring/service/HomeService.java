package com.huangsikai.eventresspring.service;

import com.huangsikai.eventresspring.pojo.HomeGrid;

import java.util.List;

public interface HomeService {


    List<HomeGrid> getHomeGridList();

    String getTopInformation();

    void setTopInformation(String value);

    void addHomeGrid(HomeGrid homeGrid);
    void updateHomeGrid(HomeGrid homeGrid);
    void delHomeGrid(Integer id);
}
