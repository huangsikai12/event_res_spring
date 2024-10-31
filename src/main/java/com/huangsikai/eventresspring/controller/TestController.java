package com.huangsikai.eventresspring.controller;


import com.google.gson.Gson;
import com.huangsikai.eventresspring.pojo.User;
import com.huangsikai.eventresspring.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RedisService redisService;




    @GetMapping("/get")
    public String get()
    {

        return redisService.get("test");
    }

    @GetMapping("/settime")
    public boolean settime()
    {

        return redisService.expire("test",10);
    }
}
