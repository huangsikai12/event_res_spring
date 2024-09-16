package com.huangsikai.eventresspring.mapper;

import com.huangsikai.eventresspring.pojo.EmailMessage;
import com.huangsikai.eventresspring.pojo.Event;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmailMessageMapper {

    void addMessage(EmailMessage emailMessage);
    List<EmailMessage> findMessageByIp(String ip,Long send_time);
}
