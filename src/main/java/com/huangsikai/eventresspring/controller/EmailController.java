package com.huangsikai.eventresspring.controller;


import com.google.gson.Gson;
import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.config.JwtConfig;
import com.huangsikai.eventresspring.mq.Config;
import com.huangsikai.eventresspring.mq.Producer;
import com.huangsikai.eventresspring.po.SendEmailPo;
import com.huangsikai.eventresspring.pojo.EmailMessage;
import com.huangsikai.eventresspring.pojo.User;
import com.huangsikai.eventresspring.service.EmailMessageService;
import com.huangsikai.eventresspring.service.EmailService;
import com.huangsikai.eventresspring.service.RedisService;
import com.huangsikai.eventresspring.service.UserService;
import com.huangsikai.eventresspring.utils.IPUtil;
import com.huangsikai.eventresspring.utils.VerificationCodeUtils;
import com.huangsikai.eventresspring.vo.UserVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {


    Gson gson = new Gson();
    @Autowired
    JwtConfig jwtConfig;
    @Autowired
    EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    RedisService redisService;
    @Resource
    EmailMessageService messageService;
    @Autowired
    Producer producer;
    @PostMapping("/sendCode")
    public Result<String> sendEmail(@RequestBody SendEmailPo emailBody, HttpServletRequest httpRequest)
    {

        emailBody.setIp(IPUtil.getIpAddress(httpRequest));
//        if (sendLimit(emailBody,4,180000L)) {
//            return new Result<>(404,"发送过于频繁！","");
//        }
        if (sendLimit(emailBody,10,86400000L)) {
            return new Result<>(404,"一天只能发送10条验证码！","");
        }
        try
        {
            if (emailBody.getAction().equals("1"))
            {
                User u = userService.getUserByPhone(emailBody.getEmail());
                if (u == null)
                {
                    return new Result<>(404,"改邮箱没有绑定任何账号！","");
                }
            }
            producer.send(Config.SEND_EMAIL,gson.toJson(emailBody));
            return new Result<>(200,"发送成功","");
        }catch (Exception e)
        {

            return new Result<>(404,"发送失败",e.getMessage());
        }
    }

    public boolean sendLimit(SendEmailPo emailBody,Integer limit,Long time)
    {
        return  messageService.findMessageByIp(emailBody.getIp(),new Date().getTime()-time).size() >= limit;
    }

    @PostMapping("/verCode/{code}")
    public Result<Object> verCode(@RequestBody SendEmailPo emailBody,@PathVariable String code)
    {
        String token = "";
        String redis_code = redisService.get(emailBody.getAction()+emailBody.getUid() + "," + emailBody.getEmail());
        if (code.equals(redis_code))
        {
            if (emailBody.getAction().equals("1"))
            {
                User u = userService.getUserByPhone(emailBody.getEmail());
                token = jwtConfig.createToken(gson.toJson(new UserVo(u.getId(),u.getName(),u.getUid(),u.getRoleId(),"")));
                u.setToken(token);
                return new Result<>(200,"登陆成功",new UserVo(u.getId(),u.getName(),u.getUid(),u.getRoleId(),u.getToken()));
            }
            redisService.remove(emailBody.getAction()+emailBody.getUid() + "," + emailBody.getEmail());
            return new Result<>(200,"验证成功",token);
        }else
        {
            return new Result<>(404,"验证失败","");
        }
    }



}
