package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.mapper.EventMapper;
import com.huangsikai.eventresspring.mapper.JoinedInfoMapper;
import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.JoinedInfo;
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
    public List<JoinedInfo> getJoinedInfoByUserId(Integer uid) {
        return joinedInfoMapper.getJoinedInfoByUserId(uid);
    }
    @Override
    public List<Event> getJoinEventByUserId(Integer uid) {
        return joinedInfoMapper.getJoinEventByUserId(uid);
    }

    @Override
    public void addJoin(Integer uid, Integer event_id) {
        joinedInfoMapper.addJoin(uid,event_id);
    }

    @Override
    public void cancelJoin(Integer uid, Integer event_id) {
        joinedInfoMapper.cancelJoin(uid,event_id);
    }

    @Override
    public void signJoin(Integer uid, Integer event_id) {

    }

    @Override
    public void updateJoin(JoinedInfo joinedInfo) {

        log.error(joinedInfo.toString());
        joinedInfoMapper.updateJoin(joinedInfo);
    }
}
