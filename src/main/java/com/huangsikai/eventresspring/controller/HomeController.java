package com.huangsikai.eventresspring.controller;


import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.pojo.HomeGrid;
import com.huangsikai.eventresspring.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@Slf4j
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

    @PostMapping("/setHomeGrid")
    public Result<HomeGrid> setHomeGrid(@RequestBody HomeGrid homeGrid) {
        try {
            homeService.addHomeGrid(homeGrid);
            return new Result<>(200,"设置成功",homeGrid);
        }catch (Exception e)
        {
            return new Result<>(404,"设置失败",null);
        }
    }

    @PostMapping("/updateHomeGrid")
    public Result<HomeGrid> updateHomeGrid(@RequestBody HomeGrid homeGrid) {
        try {
            homeService.updateHomeGrid(homeGrid);
            return new Result<>(200,"设置成功",homeGrid);
        }catch (Exception e)
        {
            log.error(e.getMessage());

            return new Result<>(404,"设置失败",null);
        }
    }

    @GetMapping("/delHomeGrid/{id}")
    public Result<HomeGrid> updateHomeGrid(@PathVariable Integer id) {
        try {
            homeService.delHomeGrid(id);
            return new Result<>(200,"删除成功",null);
        }catch (Exception e)
        {
            log.error(e.getMessage());
            return new Result<>(404,"失败",null);
        }
    }




}
