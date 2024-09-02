package com.huangsikai.eventresspring.mapper;

import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.JoinedInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JoinedInfoMapper {

    List<JoinedInfo> getJoinedInfoByUserId(Integer uid);
    List<Event> getJoinEventByUserId(Integer uid);
    void addJoin(Integer uid,Integer event_id);
    void cancelJoin(Integer uid,Integer event_id);
    void updateJoin(JoinedInfo joinedInfo);

}
