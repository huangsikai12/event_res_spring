package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.JoinedInfo;
import com.huangsikai.eventresspring.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JoinedInfoService {


    List<UserVo> getJoinedInfoByEventId(String eid,Integer status);
    List<JoinedInfo> getJoinedInfoByStatus(Integer status);
    List<JoinedInfo> getJoinedInfoByUserId(String uid,String eid);
    List<Event> getJoinEventByUserId(String uid);
    void addJoin(String uid,String event_id,Integer status);
    void cancelJoin(String uid,String event_id);
    void signJoin(String uid,String event_id);
    void updateJoin(JoinedInfo joinedInfo);
}
