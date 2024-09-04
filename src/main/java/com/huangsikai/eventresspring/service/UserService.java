package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    User getUserById(String id);
    User getUserByInfo(UserPo user);
    void addUser(User user);
}
