package com.huangsikai.eventresspring.service;

import com.huangsikai.eventresspring.pojo.EmailMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailMessageService {
    void addMessage(EmailMessage emailMessage);
    List<EmailMessage> findMessageByIp(String ip,Long send_time);
}
