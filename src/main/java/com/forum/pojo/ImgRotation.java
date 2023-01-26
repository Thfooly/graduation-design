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
@Table(name = "img_rotation")
public class ImgRotation {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer imgId;
    //@Transient表示不需要持久化到数据库
}
