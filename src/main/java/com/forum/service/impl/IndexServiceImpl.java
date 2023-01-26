package com.forum.service.impl;

import com.forum.dao.ImgRotationDao;
import com.forum.dao.ImgSrcDao;
import com.forum.dao.NoteConstantDao;
import com.forum.dao.UserDao;
import com.forum.pojo.ImgRotation;
import com.forum.pojo.NoteConstant;
import com.forum.pojo.User;
import com.forum.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired(required = false)
    private UserDao userDao;
    @Autowired(required = false)
    private ImgRotationDao imgRotationDao;
    @Autowired(required = false)
    private ImgSrcDao imgSrcDao;
    @Autowired(required = false)
    private NoteConstantDao noteConstantDao;

    @Override
    public User findByUid(String uid) {
        return userDao.findByUid(uid);
    }

    @Override
    public List<String> getRotation() {
        List<ImgRotation> imgRotation = imgRotationDao.findAll();
        List<String> imgSrcs = new ArrayList<>();
        for (ImgRotation rotation : imgRotation) {
//            imgSrcs.add(imgSrcDao.findById(rotation.getImgId()).getImgPath());
        }
        return imgSrcs;
    }

    @Override
    public List<NoteConstant> getNewNotes() {
        List<NoteConstant> noteConstants = noteConstantDao.findNewNotes();
        return noteConstants;
    }
}
