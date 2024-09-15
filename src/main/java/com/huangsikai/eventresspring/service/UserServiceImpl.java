package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.mapper.UserMapper;
import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.User;
import com.huangsikai.eventresspring.utils.PasswordEncryptor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Resource
    UserMapper userMapper;
    @Override
    public User getUserById(String uid) {
        return userMapper.getUserById(uid);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public User getUserByInfo(UserPo user) throws Exception {
        user.setPwd(PasswordEncryptor.encrypt(user.getPwd()));
        return userMapper.getUserByInfo(user);
    }

    @Override
    public void addUser(User user) throws Exception {
        user.setPwd(PasswordEncryptor.encrypt(user.getPwd()));
        userMapper.addUser(user);
    }
}
