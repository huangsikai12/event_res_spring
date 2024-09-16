package com.huangsikai.eventresspring.service;


import com.huangsikai.eventresspring.config.MailSenderConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class EmailService {

    private final MailSenderConfig senderConfig;

    /**
     * 发送邮件
     *
     * @param to      收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public String sendMail(String to, String subject, String content) throws MessagingException {
        JavaMailSenderImpl mailSender = senderConfig.getSender();
        // 创建邮件消息
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(Objects.requireNonNull(mailSender.getUsername()));
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        // 发送邮件
        mailSender.send(message);
        return Objects.requireNonNull(mailSender.getUsername());
    }
}

