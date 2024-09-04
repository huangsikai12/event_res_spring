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
    public List<Event> getEventList(Integer status) {
        return eventMapper.getEventList(status);
    }

    @Override
    public void addEvent(Event event) {
        eventMapper.addEvent(event);
    }

    @Override
    public List<Event> verEventSign(String eid, String signPwd) {
        return eventMapper.verEventSign(eid, signPwd);
    }

    @Override
    public void deleteEvent(String eid) {
        eventMapper.deleteEvent(eid);
    }

    @Override
    public void updateEvent(String eid, Integer status,String signPwd) {
        eventMapper.updateEvent(eid, status,signPwd);
    }
}
