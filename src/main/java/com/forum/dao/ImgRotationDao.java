package com.forum.dao;

import com.forum.pojo.ImgRotation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ImgRotationDao extends Mapper<ImgRotation>{

    @Select("select * form img_rotation")
    List<ImgRotation> findAll();
}
