package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.mapper.EventMapper;
import com.huangsikai.eventresspring.mapper.JoinedInfoMapper;
import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.JoinedInfo;
import com.huangsikai.eventresspring.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JoinedInfoServiceImpl implements JoinedInfoService {

    @Autowired
    JoinedInfoMapper joinedInfoMapper;
    @Autowired
    EventMapper eventMapper;

    @Override
    public List<UserVo> getJoinedInfoByEventId(String eid,Integer status) {
        return joinedInfoMapper.getJoinedInfoByEventId(eid,status);
    }

    @Override
    public List<JoinedInfo> getJoinedInfoByUserId(String uid,String eid) {
        return joinedInfoMapper.getJoinedInfoByUserId(uid,eid);
    }
    @Override
    public List<Event> getJoinEventByUserId(String uid) {
        return joinedInfoMapper.getJoinEventByUserId(uid);
    }

    @Override
    public void addJoin(String uid, String event_id,Integer status) {
        joinedInfoMapper.addJoin(uid,event_id,status);
    }

    @Override
    public void cancelJoin(String uid, String event_id) {
        joinedInfoMapper.cancelJoin(uid,event_id);
    }

    @Override
    public void signJoin(String uid, String event_id) {

    }

    @Override
    public void updateJoin(JoinedInfo joinedInfo) {

        log.error(joinedInfo.toString());
        joinedInfoMapper.updateJoin(joinedInfo);
    }
}
