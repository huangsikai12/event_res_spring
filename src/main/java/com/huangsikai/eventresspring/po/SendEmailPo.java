package com.huangsikai.eventresspring.po;


import lombok.Data;

@Data
public class SendEmailPo {

    private String uid;
    private String email;
    private String action;
    //reg-0 login-1 find-2 reset-3



}
