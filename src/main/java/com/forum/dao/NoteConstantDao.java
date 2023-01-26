package com.forum.dao;

import com.forum.pojo.NoteConstant;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface NoteConstantDao extends Mapper<NoteConstant>{

    @Select("select * form note_constant order by create_time desc limit(0, 5)")
    List<NoteConstant> findNewNotes();
}
