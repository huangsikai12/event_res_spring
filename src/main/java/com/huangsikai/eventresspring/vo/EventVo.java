package com.huangsikai.eventresspring.vo;

import com.huangsikai.eventresspring.pojo.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventVo{


    private String id;
    private Integer status;
    private String title;
    private String description;
    private String place;
    private String time;

}
