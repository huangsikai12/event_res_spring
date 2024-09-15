package com.huangsikai.eventresspring.service;

import com.huangsikai.eventresspring.mapper.EmailMessageMapper;
import com.huangsikai.eventresspring.pojo.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailMessageServiceImpl implements EmailMessageService {

    @Autowired
    EmailMessageMapper emailMessageMapper;

    @Override
    public void addMessage(EmailMessage emailMessage) {
        emailMessageMapper.addMessage(emailMessage);
    }
}
