package com.huangsikai.eventresspring.controller;


import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.JoinedInfo;
import com.huangsikai.eventresspring.service.EventService;
import com.huangsikai.eventresspring.service.JoinedInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/join")
public class JoinedInfoController {

    @Autowired
    JoinedInfoService joinedInfoService;


    @GetMapping("/get/user")
    public Result<List<JoinedInfo>> getJoinedInfoByUserId(@RequestParam Integer uid)
    {
        try
        {
            return new Result<List<JoinedInfo>>(200,"获取成功",joinedInfoService.getJoinedInfoByUserId(uid));
        }
        catch (Exception e)
        {
            return new Result(404,"获取失败",e.getMessage());
        }
    }
    @GetMapping("/get/user/detail")
    public Result<List<Event>> getJoinEventByUserId(@RequestParam Integer uid)
    {
        try
        {
            return new Result<List<Event>>(200,"获取成功",joinedInfoService.getJoinEventByUserId(uid));
        }
        catch (Exception e)
        {
            return new Result(404,"获取失败",e.getMessage());
        }
    }
    @GetMapping("/add")
    public Result<String> addJoin(@RequestParam Integer uid,@RequestParam Integer eid)
    {
        try
        {
            joinedInfoService.addJoin(uid,eid);
            return new Result<>(200,"报名成功",null);
        }
        catch (Exception e)
        {
            return new Result<>(404,"报名失败",e.getMessage());
        }
    }
    @GetMapping("/cancel")
    public Result<String> cancelJoin(@RequestParam Integer uid,@RequestParam Integer eid)
    {
        try
        {
            joinedInfoService.cancelJoin(uid,eid);
            return new Result<>(200,"取消报名成功",null);
        }
        catch (Exception e)
        {
            return new Result<>(404,"取消报名失败",e.getMessage());
        }
    }

}
