package com.huangsikai.eventresspring.controller;


import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.pojo.HomeGrid;
import com.huangsikai.eventresspring.service.HomeGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeGridService homeGridService;

    @GetMapping("/getHomeGrids")
    public Result<List<HomeGrid>> getHomeGrids() {
        try {
           return new Result<>(200,"获取成功",homeGridService.getHomeGridList());
        }catch (Exception e)
        {
            return new Result<>(404,"获取失败",null);
        }

    }
}
