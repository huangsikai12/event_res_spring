package com.huangsikai.eventresspring.mq;


import com.google.gson.Gson;
import com.huangsikai.eventresspring.po.SendEmailPo;
import com.huangsikai.eventresspring.pojo.EmailMessage;
import com.huangsikai.eventresspring.service.EmailMessageService;
import com.huangsikai.eventresspring.service.EmailService;
import com.huangsikai.eventresspring.service.RedisService;
import com.huangsikai.eventresspring.service.UserService;
import com.huangsikai.eventresspring.utils.IPUtil;
import com.huangsikai.eventresspring.utils.VerificationCodeUtils;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Consumer {

    Gson gson = new Gson();
    @Autowired
    EmailService emailService;
    @Autowired
    RedisService redisService;
    @Resource
    EmailMessageService messageService;
    @RabbitHandler
    @RabbitListener(queues = Config.SEND_EMAIL)
    public void process(String testMessage) throws MessagingException {
        log.error("消费消息"+testMessage);
        SendEmailPo emailBody =gson.fromJson(testMessage,SendEmailPo.class);
        String code = VerificationCodeUtils.generateCode(6);
        String subject = switch (emailBody.getAction()) {
            case "0" -> "欢迎注册社团报名平台";
            case "1" -> "欢迎登录社团报名平台";
            case "2" -> "欢迎再次回到社团报名平台";
            default -> "欢迎使用社团报名平台";
        };
        String content = "您的验证码是："+code+"\n验证码5分钟内有效，请及时注册!";
        redisService.set(emailBody.getAction()+emailBody.getUid()+","+emailBody.getEmail(),code,300);
        try
        {
            String sender = emailService.sendMail(emailBody.getEmail(), subject, content);
            messageService.addMessage(new EmailMessage(null,emailBody.getUid(),emailBody.getEmail(),subject+content,new Date().getTime(),"0",emailBody.getIp(),sender));

        }catch (Exception e)
        {
            log.error(e.getMessage()+"第一次选择失败，进行第二次选择");
            try
            {
                String sender = emailService.sendMail(emailBody.getEmail(), subject, content);
                messageService.addMessage(new EmailMessage(null,emailBody.getUid(),emailBody.getEmail(),subject+content,new Date().getTime(),"0",emailBody.getIp(),sender));
            }
            catch (Exception e1)
            {
                log.error(e1.getMessage()+"第二次选择失败，进行第三次选择");
                String sender = emailService.sendMail(emailBody.getEmail(), subject, content);
                messageService.addMessage(new EmailMessage(null,emailBody.getUid(),emailBody.getEmail(),subject+content,new Date().getTime(),"0",emailBody.getIp(),sender));

            }

        }


    }

}
