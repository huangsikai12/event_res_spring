package com.huangsikai.eventresspring.controller;



import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.User;
import com.huangsikai.eventresspring.service.UserService;
import com.huangsikai.eventresspring.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public Result<UserVo> Login(@RequestBody UserPo user)
    {
        User u = userService.getUserByInfo(user);
        if (u!=null)
        {
            return new Result<UserVo>(200,"登陆成功",new UserVo(u.getId(),u.getName(),u.getUid(),u.getRoleId()));

        }
        return new Result(404,"登陆失败",null);
    }
    @PostMapping("/add")
    public Result<String> AddUser(@RequestBody User user)
    {
        try
        {
            userService.addUser(user);
            return new Result<String>(200,"添加成功",user.toString());
        }catch (Exception e)
        {
            return new Result<String>(404,"添加失败",e.getMessage());
        }

    }
}
