package com.forum.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class NoteReply {

    private Integer noteRId;
    private String noteId;
    private String userId;
    private Date createTime;
    private String textarea;
}
