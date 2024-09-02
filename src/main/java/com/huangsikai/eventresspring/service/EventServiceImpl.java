package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.mapper.EventMapper;
import com.huangsikai.eventresspring.pojo.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{




    @Autowired
   EventMapper eventMapper;


    @Override
    public List<Event> getEventList() {
        return eventMapper.getEventList();
    }

    @Override
    public void addEvent(Event event) {
        eventMapper.addEvent(event);
    }

    @Override
    public List<Event> verEventSign(Integer eid, String signPwd) {
        return eventMapper.verEventSign(eid, signPwd);
    }

    @Override
    public void deleteEvent(Integer eid) {
        eventMapper.deleteEvent(eid);
    }

    @Override
    public void updateEvent(Integer eid, Integer status,String signPwd) {
        eventMapper.updateEvent(eid, status,signPwd);
    }
}
