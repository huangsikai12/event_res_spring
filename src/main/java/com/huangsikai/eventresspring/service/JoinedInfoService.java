package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.JoinedInfo;
import com.huangsikai.eventresspring.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JoinedInfoService {


    List<UserVo> getJoinedInfoByEventId(Integer eid,Integer status);
    List<JoinedInfo> getJoinedInfoByUserId(Integer uid);
    List<Event> getJoinEventByUserId(Integer uid);
    void addJoin(Integer uid,Integer event_id);
    void cancelJoin(Integer uid,Integer event_id);
    void signJoin(Integer uid,Integer event_id);
    void updateJoin(JoinedInfo joinedInfo);
}
