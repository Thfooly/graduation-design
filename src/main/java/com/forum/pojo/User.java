package com.forum.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private String username;
    private String password;

    private String email;
    private String phone;

    private Date createTime;
    private Integer emailVerify;
    //0管理 1官方 2用户
    private Integer userMark;

//    private String userImg;
}
