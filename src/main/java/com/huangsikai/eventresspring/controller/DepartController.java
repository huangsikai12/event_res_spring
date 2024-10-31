package com.huangsikai.eventresspring.controller;


import com.google.gson.Gson;
import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.pojo.Department;
import com.huangsikai.eventresspring.pojo.HomeGrid;
import com.huangsikai.eventresspring.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@Slf4j
public class DepartController {

    @Autowired
    DepartmentService departmentService;
    @GetMapping("/getAllDeparts")
    public Result<List<Department>> getAllDeparts() {
        try {
            return new Result<>(200,"获取成功",departmentService.getDepartmentList());
        }catch (Exception e)
        {
            return new Result<>(404,"获取失败",null);
        }
    }

    @GetMapping("/getDepart/{id}")
    public Result<Department> getAllDeparts(@PathVariable Integer id) {
        try {
            return new Result<>(200,"获取成功",departmentService.getDepartmentById(id));
        }catch (Exception e)
        {
            return new Result<>(404,"获取失败",null);
        }
    }

    @PostMapping("/addDepart")
    public Result<String> addDepart(@RequestBody Department department) {
        try {
            departmentService.addDepartment(department);
            return new Result<>(200,"新增成功",new Gson().toJson(department));
        }catch (Exception e)
        {
            return new Result<>(404,"获取失败",null);
        }
    }



}
