package com.huangsikai.eventresspring.controller;


import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.pojo.HomeGrid;
import com.huangsikai.eventresspring.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/getHomeGrids")
    public Result<List<HomeGrid>> getHomeGrids() {
        try {
           return new Result<>(200,"获取成功",homeService.getHomeGridList());
        }catch (Exception e)
        {
            return new Result<>(404,"获取失败",null);
        }

    }

    @GetMapping("/getTopInfo")
    public Result<String> getTopInformation() {
        try {
            return new Result<>(200,"获取成功",homeService.getTopInformation());
        }catch (Exception e)
        {
            return new Result<>(404,"获取失败",null);
        }

    }

    @PostMapping("/setTopInfo")
    public Result<String> setTopInformation(@RequestParam String information) {
        try {
            homeService.setTopInformation(information);
            return new Result<>(200,"设置成功",information);
        }catch (Exception e)
        {
            return new Result<>(404,"设置失败",null);
        }

    }
}
