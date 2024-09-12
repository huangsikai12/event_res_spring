package com.huangsikai.eventresspring.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huangsikai.eventresspring.mapper.EventMapper;
import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{


    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
    @Autowired
    EventMapper eventMapper;
    @Autowired
    RedisService redisService;
    Gson gson = new Gson();


    @Override
    public List<Event> getEventList(Integer status) {
        List<Event> eventList;
        String event_json = redisService.get("event");
        if (event_json.equals("null"))
        {
            eventList = eventMapper.getEventList(status);
            redisService.set("event",gson.toJson(eventList));
        }
        else
        {
            eventList = gson.fromJson(event_json, new TypeToken<List<Event>>(){}.getType());
            if (status!=null)
            {
                eventList = eventList.stream().filter(event -> Objects.equals(event.getStatus(), status))
                        .collect(Collectors.toList());
            }
        }
        return eventList;
    }

    @Override
    public void addEvent(Event event) {
        redisService.remove("event");
        eventMapper.addEvent(event);
    }

    @Override
    public List<Event> verEventSign(String eid, String signPwd) {
        redisService.remove("event");
        return eventMapper.verEventSign(eid, signPwd);
    }

    @Override
    public void deleteEvent(String eid) {
        redisService.remove("event");
        eventMapper.deleteEvent(eid);
    }

    @Override
    public void updateEvent(String eid, Integer status,String signPwd) {
        redisService.remove("event");
        eventMapper.updateEvent(eid, status,signPwd);
    }
}
