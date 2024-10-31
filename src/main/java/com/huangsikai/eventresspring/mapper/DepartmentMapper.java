package com.huangsikai.eventresspring.mapper;

import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.Department;
import com.huangsikai.eventresspring.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    Department getDepartmentById(Integer id);
    List<Department> getDepartmentList();
    void addDepartment(Department department);
}
