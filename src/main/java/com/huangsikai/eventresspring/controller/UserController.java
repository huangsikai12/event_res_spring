package com.huangsikai.eventresspring.controller;



import com.google.gson.Gson;
import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.config.JwtConfig;
import com.huangsikai.eventresspring.po.UserPo;
import com.huangsikai.eventresspring.pojo.User;
import com.huangsikai.eventresspring.service.UserService;
import com.huangsikai.eventresspring.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtConfig jwtConfig;
    // 创建Gson对象
    Gson gson = new Gson();


    @PostMapping("/login")
    public Result<UserVo> Login(@RequestBody UserPo user)
    {
        try {
            User u = userService.getUserByInfo(user);
            if (u!=null)
            {
                String token = jwtConfig.createToken(gson.toJson(new UserVo(u.getId(),u.getName(),u.getUid(),u.getRoleId(),"")));
                u.setToken(token);
                return new Result<UserVo>(200,"登陆成功",new UserVo(u.getId(),u.getName(),u.getUid(),u.getRoleId(),u.getToken()));

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return new Result<>(404,"登陆失败",null);
    }

    @GetMapping("/get")
    public Result<UserVo> getUserByUserId(@RequestParam String uid)
    {
        User u = null;
        try {
            u = userService.getUserById(uid);
        } catch (Exception ignored) {
        }
        if (u!=null)
        {
            return new Result<UserVo>(200,"获取成功",new UserVo(u.getId(),u.getName(),u.getUid(),u.getRoleId(),""));

        }
        return new Result(404,"获取失败",null);
    }
    @PostMapping("/add")
    public Result<String> addUser(@RequestBody User user)
    {
        try
        {
            User u = userService.getUserById(user.getUid());
            u = u==null?userService.getUserByPhone(user.getPhone()):u;
            if (u==null)
            {
                userService.addUser(user);
                return new Result<String>(200,"注册成功",user.toString());
            }
        }catch (Exception e)
        {
            return new Result<String>(404,"注册失败，网络问题",e.getMessage());
        }
        return new Result<String>(404,"注册失败,该学号或邮箱已被注册！","1111");

    }
}
