package com.huangsikai.eventresspring.mapper;

import com.huangsikai.eventresspring.pojo.Event;
import com.huangsikai.eventresspring.pojo.JoinedInfo;
import com.huangsikai.eventresspring.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JoinedInfoMapper {

    List<JoinedInfo> getJoinedInfoByUserId(String uid,String eid);
    List<JoinedInfo> getJoinedInfoByStatus(Integer status);
    List<Event> getJoinEventByUserId(String uid);
    List<UserVo> getJoinedInfoByEventId(String eid,Integer status);
    void addJoin(String uid,String event_id,Integer status);
    void cancelJoin(String uid,String event_id);
    void updateJoin(JoinedInfo joinedInfo);

}
