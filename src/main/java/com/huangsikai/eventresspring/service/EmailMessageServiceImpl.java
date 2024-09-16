package com.huangsikai.eventresspring.service;

import com.huangsikai.eventresspring.mapper.EmailMessageMapper;
import com.huangsikai.eventresspring.pojo.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmailMessageServiceImpl implements EmailMessageService {

    @Autowired
    EmailMessageMapper emailMessageMapper;

    @Override
    public void addMessage(EmailMessage emailMessage) {
        emailMessageMapper.addMessage(emailMessage);
    }

    @Override
    public List<EmailMessage> findMessageByIp(String ip,Long send_time) {
        return emailMessageMapper.findMessageByIp(ip,send_time);
    }
}
