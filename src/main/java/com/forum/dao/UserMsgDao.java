package com.forum.dao;

import com.forum.pojo.UserMsg;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

public interface UserMsgDao extends Mapper<UserMsg>{

//    @Insert("insert into userMsg(user_id,user_level,img_id) values " +
//            "(#{msg.userId}, #{msg.userLevel}, #{msg.imgId})")
//    void insertUserMsg(UserMsg msg);

    @Select("select * from userMsg where user_id = #{id}")
    @Results(id = "userMsgMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "user_level",property = "userLevel"),
            @Result(column = "img_id",property = "imgId"),
    })
    UserMsg findByUserId(Integer id);
}
