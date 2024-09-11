package com.huangsikai.eventresspring.mapper;


import com.huangsikai.eventresspring.pojo.HomeGrid;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeGridMapper {

    List<HomeGrid> getHomeGridList();
}
