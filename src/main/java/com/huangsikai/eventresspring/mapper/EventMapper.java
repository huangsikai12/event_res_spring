package com.huangsikai.eventresspring.mapper;

import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    List<Event> getEventList(Integer status);
//    User getUserByInfo(UserPo user);
    void deleteEvent(String eid);
    void addEvent(Event event);
    List<Event> verEventSign(String eid,String signPwd);
    void updateEvent(String eid,Integer status,String signPwd);
}
