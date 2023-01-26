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
@Table(name = "img_src")
public class ImgSrc {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String imgPath;
}
