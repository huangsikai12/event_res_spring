package com.huangsikai.eventresspring.controller;


import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.JoinedInfo;
import com.huangsikai.eventresspring.service.EventService;
import com.huangsikai.eventresspring.service.JoinedInfoService;
import com.huangsikai.eventresspring.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/join")
public class JoinedInfoController {

    @Autowired
    JoinedInfoService joinedInfoService;


    @GetMapping("/get/user")
    public Result<List<JoinedInfo>> getJoinedInfoByUserId(@RequestParam String uid,@RequestParam @Nullable String eid)
    {
        try
        {
            return new Result<List<JoinedInfo>>(200,"获取成功",joinedInfoService.getJoinedInfoByUserId(uid,eid));
        }
        catch (Exception e)
        {
            return new Result(404,"获取失败",e.getMessage());
        }
    }
    @GetMapping("/get/user/detail")
    public Result<List<Event>> getJoinEventByUserId(@RequestParam String uid)
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
    public Result<String> addJoin(@RequestParam String uid,@RequestParam String eid)
    {
        try
        {
            joinedInfoService.addJoin(uid,eid,0);
            return new Result<>(200,"报名成功",null);
        }
        catch (Exception e)
        {
            return new Result<>(404,"报名失败",e.getMessage());
        }
    }
    @GetMapping("/cancel")
    public Result<String> cancelJoin(@RequestParam String uid,@RequestParam String eid)
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

    @GetMapping("/get/event/join")
    public Result<List<UserVo>> getJoinedInfoByEventId(@RequestParam String eid,@RequestParam @Nullable Integer status) {
        try
        {
            return new Result<List<UserVo>>(200,"获取成功",joinedInfoService.getJoinedInfoByEventId(eid,status));
        }
        catch (Exception e)
        {
            return new Result(404,"获取失败",e.getMessage());
        }
    }

    @GetMapping("/get/status")
    public Result<List<JoinedInfo>> getJoinedInfoByStatus(@RequestParam Integer status) {
        try
        {
            return new Result<List<JoinedInfo>>(200,"获取成功",joinedInfoService.getJoinedInfoByStatus(status));
        }
        catch (Exception e)
        {
            return new Result(404,"获取失败",e.getMessage());
        }
    }
    @PostMapping("/set/status")
    public Result<JoinedInfo> setJoinedInfoByStatus(@RequestBody JoinedInfo joinedInfo) {
        try
        {
            joinedInfoService.updateJoin(joinedInfo);
            return new Result<JoinedInfo>(200,"设置成功",joinedInfo);
        }
        catch (Exception e)
        {
            return new Result(404,"设置失败",e.getMessage());
        }
    }


}
