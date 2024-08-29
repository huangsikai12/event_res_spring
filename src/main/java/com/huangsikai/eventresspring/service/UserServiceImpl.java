package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.mapper.UserMapper;
import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Resource
    UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByInfo(UserPo user) {
        return userMapper.getUserByInfo(user);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
