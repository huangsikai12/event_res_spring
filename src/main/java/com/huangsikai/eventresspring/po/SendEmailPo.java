package com.huangsikai.eventresspring.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailPo implements Serializable {

    private String uid;
    private String email;
    private String action;
    private String ip;
    //reg-0 login-1 find-2 reset-3



}
