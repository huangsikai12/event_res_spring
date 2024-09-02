package com.huangsikai.eventresspring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinedInfo {

    private Integer id;
    private String uid;
    private Integer eid;
    private Integer status;
}
