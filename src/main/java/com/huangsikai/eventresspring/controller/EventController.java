package com.huangsikai.eventresspring.controller;


import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.JoinedInfo;
import com.huangsikai.eventresspring.pojo.User;
import com.huangsikai.eventresspring.service.EventService;
import com.huangsikai.eventresspring.service.JoinedInfoService;
import com.huangsikai.eventresspring.service.UserService;
import com.huangsikai.eventresspring.vo.UserVo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;
    @Autowired
    JoinedInfoService joinedInfoService;


    @GetMapping("/all")
    public Result<List<Event>> getEventList(@RequestParam @Nullable Integer status)
    {
        try
        {
            return new Result<>(200,"获取成功",eventService.getEventList(status));
        }
        catch (Exception e)
        {
            return new Result(404,"获取失败",e.getMessage());
        }

    }

    @PostMapping("/delete/{eid}")
    public Result<String> deleteEvent(@PathVariable String eid)
    {
        try
        {
            eventService.deleteEvent(eid);
            return new Result<>(200,"删除成功",null);
        }
        catch (Exception e)
        {
            return new Result<>(404,"删除失败",
                    e.getMessage());
        }
    }

    @PostMapping("/add")
    public Result<String> addEvent(@RequestBody Event event)
    {
        try
        {
            eventService.addEvent(event);
            return new Result<>(200,"新增成功",event.toString());
        }
        catch (Exception e)
        {
            return new Result<>(404,"新增失败",
                    e.getMessage());
        }
    }

    @PostMapping("/update/{eid}/{status}")
    public Result<String> updateStatus(@RequestBody UserVo userVo, @PathVariable String eid, @PathVariable Integer status)
    {
        try
        {
            eventService.updateEvent(eid,status,null);
            return new Result<>(200,"更新成功",null);
        }
        catch (Exception e)
        {
            return new Result<>(404,"更新失败",
                    e.getMessage());
        }
    }

    @PostMapping("/setSignPwd/{eid}")
    public Result<String> updateSignPwd(@PathVariable String eid, @RequestParam String signPwd)
    {
        try
        {
            eventService.updateEvent(eid,null,signPwd);
            return new Result<>(200,"设置成功",null);
        }
        catch (Exception e)
        {
            return new Result<>(404,"设置失败",
                    e.getMessage());
        }
    }

    @PostMapping("/verSignPwd/{eid}")
    @Transactional
    public Result<String> verSignPwd(@RequestBody UserVo userVo, @PathVariable String eid, @RequestParam String signPwd,@RequestParam @Nullable  Integer status)
    {
        if (status == null)
        {
            status = 1;
        }
        try
        {
            List<Event> events = eventService.verEventSign(eid, signPwd);
            List<JoinedInfo> joinedInfoByUserId = joinedInfoService.getJoinedInfoByUserId(userVo.getUid(), eid);
            if (!joinedInfoByUserId.isEmpty() && joinedInfoByUserId.get(0).getStatus() ==1)
            {
                return new Result<>(404,"验证失败","签到过了");
            }
            if (status!=1 && !events.isEmpty() )
            {
                joinedInfoService.addJoin(userVo.getUid(),eid,status);
            }else
            {
                if (!events.isEmpty() && events.get(0).getStatus() == 1)
                {

                    joinedInfoService.updateJoin(new JoinedInfo(0,userVo.getUid(),eid,status,""));
                }
            }
            return new Result<>(200, !events.isEmpty() ?"验证并签到成功":"签到码错误",null);
        }
        catch (Exception e)
        {
            return new Result<>(404,"验证失败",
                    e.getMessage());
        }
    }

}
