package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.mapper.DepartmentMapper;
import com.huangsikai.eventresspring.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements  DepartmentService{


    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public Department getDepartmentById(Integer id) {
        return departmentMapper.getDepartmentById(id);
    }

    @Override
    public List<Department> getDepartmentList() {
        return departmentMapper.getDepartmentList();
    }

    @Override
    public void addDepartment(Department department) {
        departmentMapper.addDepartment(department);
    }
}
