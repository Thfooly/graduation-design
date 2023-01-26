package com.forum.service;

import com.forum.pojo.NoteConstant;
import com.forum.pojo.User;

import java.util.List;

public interface IndexService {

    User findByUid(String uid);

    List<String> getRotation();

    List<NoteConstant> getNewNotes();

}
