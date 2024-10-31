package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.Department;
import com.huangsikai.eventresspring.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {


    Department getDepartmentById(Integer id);
    List<Department> getDepartmentList();
    void addDepartment(Department department);
}
