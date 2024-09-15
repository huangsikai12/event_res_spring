package com.huangsikai.eventresspring.controller;


import com.google.gson.Gson;
import com.huangsikai.eventresspring.Result;
import com.huangsikai.eventresspring.config.JwtConfig;
import com.huangsikai.eventresspring.po.SendEmailPo;
import com.huangsikai.eventresspring.pojo.EmailMessage;
import com.huangsikai.eventresspring.pojo.User;
import com.huangsikai.eventresspring.service.EmailMessageService;
import com.huangsikai.eventresspring.service.EmailService;
import com.huangsikai.eventresspring.service.RedisService;
import com.huangsikai.eventresspring.service.UserService;
import com.huangsikai.eventresspring.utils.VerificationCodeUtils;
import com.huangsikai.eventresspring.vo.UserVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Date;
import java.util.UUID;

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
    @PostMapping("/sendCode")
    public Result<String> sendEmail(@RequestBody SendEmailPo emailBody, HttpServletRequest httpRequest)
    {

        String code = VerificationCodeUtils.generateCode(6);
        String subject = switch (emailBody.getAction()) {
            case "0" -> "欢迎注册社团报名平台";
            case "1" -> "欢迎登录社团报名平台";
            case "2" -> "欢迎再次回到社团报名平台";
            default -> "欢迎使用社团报名平台";
        };

        String content = "您的验证码是："+code+"\n验证码5分钟内有效，请及时注册!";
        try
        {
            if (emailBody.getAction().equals("1"))
            {
                User u = userService.getUserByPhone(emailBody.getEmail());
                if (u == null)
                {
                    return new Result<String>(404,"改邮箱没有绑定任何账号！","");
                }
            }

            redisService.set(emailBody.getAction()+emailBody.getUid()+","+emailBody.getEmail(),code,300);
            emailService.sendMail(emailBody.getEmail(),subject,content);
            messageService.addMessage(new EmailMessage(null,emailBody.getUid(),emailBody.getEmail(),subject+content,String.valueOf(new Date().getTime()),"0",httpRequest.getRemoteHost()));
            return new Result<String>(200,"发送成功","");
        }catch (Exception e)
        {

            return new Result<String>(404,"发送失败",e.getMessage());
        }
    }

    @PostMapping("/verCode/{code}")
    public Result<String> verCode(@RequestBody SendEmailPo emailBody,@PathVariable String code)
    {
        String token = "";
        String redis_code = redisService.get(emailBody.getAction()+emailBody.getUid() + "," + emailBody.getEmail());
        if (code.equals(redis_code))
        {
            if (emailBody.getAction().equals("1"))
            {
                User u = userService.getUserByPhone(emailBody.getEmail());
                token = jwtConfig.createToken(gson.toJson(new UserVo(u.getId(),u.getName(),u.getUid(),u.getRoleId(),"")));
            }
            redisService.remove(emailBody.getAction()+emailBody.getUid() + "," + emailBody.getEmail());
            return new Result<String>(200,"验证成功",token);
        }else
        {
            return new Result<String>(404,"验证失败","");
        }
    }



}
