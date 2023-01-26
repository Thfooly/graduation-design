package com.forum.dao;

import com.forum.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User>{

//    @Insert("insert into user(name,username,password,email,phone,create_time, user_mark) values " +
//            "(#{user.name}, #{user.username}, #{user.password}, #{user.email}, #{user.phone}, #{user.createTime}, #{user.userMark})")
//    @Results(id = "userMap", value = {
//            @Result(id = true, column = "id", property = "id"),
//            @Result(column = "name",property = "name"),
//            @Result(column = "username",property = "username"),
//            @Result(column = "password",property = "password"),
//            @Result(column = "email",property = "email"),
//            @Result(column = "phone",property = "phone"),
//            @Result(column = "use_mark",property = "userMark"),
//            @Result(column = "create_time",property = "createTime"),
//    })
//    int insert(@Param("user")User user);

    @Select("select * from user where username = #{username}")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "email",property = "email"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "use_mark",property = "userMark"),
            @Result(column = "create_time",property = "createTime"),
    })
    User findByUsername(String username);

    @Select("select id,name,username,password,email,phone,user_mark,create_time from user where name = #{name}")
    @ResultMap("userMap")
    User findByName(String name);

    @Select("select id,name,username,password,email,phone,user_mark,create_time from user where email = #{email}")
    @ResultMap("userMap")
    User findByEmail(String email);

//    @Update("update user set email = #{user.email} where id = #{user.id}")
//    Boolean update(User user);

    @Select("select id,name,username,password,email,phone,user_mark,create_time from user where id = #{uid}")
    @ResultMap("userMap")
    User findByUid(String uid);
}
