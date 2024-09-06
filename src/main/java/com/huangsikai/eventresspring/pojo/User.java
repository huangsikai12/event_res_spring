package com.huangsikai.eventresspring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String name;
    private String pwd;
    private Integer roleId;
    private String uid;
    private String phone;
    private String token;
}
