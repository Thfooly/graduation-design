package com.forum.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_msg")
public class UserMsg {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private Integer userLevel;
    private Integer imgId;
}
