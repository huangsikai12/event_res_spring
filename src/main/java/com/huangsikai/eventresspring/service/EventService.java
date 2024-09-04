package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {


    List<Event> getEventList(Integer status);
    void addEvent(Event event);
    List<Event> verEventSign(String eid,String signPwd);
    void deleteEvent(String eid);
    void updateEvent(String eid,Integer status,String signPwd);
}
