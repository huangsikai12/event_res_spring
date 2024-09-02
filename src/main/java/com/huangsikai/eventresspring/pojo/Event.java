package com.huangsikai.eventresspring.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Event {

    private Integer id;
    // 0-待开始 1-已开始 2-已结束 3-已同步
    private Integer status;
    private String title;
    private String signPwd;
    private String description;
    private String place;
    private String time;
    private Integer joinCount;
    private Integer totalCount;
    private Integer userStatus;

}
