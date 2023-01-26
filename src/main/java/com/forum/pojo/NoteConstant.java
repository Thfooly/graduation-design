package com.forum.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "note_constant")
public class NoteConstant {

    @Id
    @KeySql(useGeneratedKeys = true)
    private String id;
    private String noteTitle;
    private String userId;
    private Date createTime;
}
