package com.huangsikai.eventresspring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmailMessage {

    private Long id;
    private String uid;
    private String email;
    private String message;
    private Long send_time;
    private String ver_time;
    private String ip;

}
