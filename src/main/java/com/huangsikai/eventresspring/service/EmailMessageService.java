package com.huangsikai.eventresspring.service;

import com.huangsikai.eventresspring.pojo.EmailMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailMessageService {
    void addMessage(EmailMessage emailMessage);
}
