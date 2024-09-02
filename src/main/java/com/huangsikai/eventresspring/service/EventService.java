package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {


    List<Event> getEventList();
    void addEvent(Event event);
    List<Event> verEventSign(Integer eid,String signPwd);
    void deleteEvent(Integer eid);
    void updateEvent(Integer eid,Integer status,String signPwd);
}
