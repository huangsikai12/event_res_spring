package com.huangsikai.eventresspring.service;

import com.huangsikai.eventresspring.pojo.HomeGrid;

import java.util.List;

public interface HomeService {


    List<HomeGrid> getHomeGridList();

    String getTopInformation();

    void setTopInformation(String value);
}
