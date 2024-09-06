package com.huangsikai.eventresspring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVo {

    private Integer id;
    private String name;
    private String uid;
    private Integer roleId;
    private String token;


}
