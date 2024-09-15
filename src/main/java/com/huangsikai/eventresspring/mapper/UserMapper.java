package com.huangsikai.eventresspring.mapper;

import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUserById(String uid);
    User getUserByInfo(UserPo user);
    void addUser(User user);
    User getUserByPhone(String phone);
}
