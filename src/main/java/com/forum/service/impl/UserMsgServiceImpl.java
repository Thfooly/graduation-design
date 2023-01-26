package com.forum.service.impl;

import com.forum.dao.UserMsgDao;
import com.forum.pojo.UserMsg;
import com.forum.service.UserMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMsgServiceImpl implements UserMsgService{

    @Autowired(required = false)
    private UserMsgDao userMsgDao;

    @Override
    public UserMsg findByUserId(Integer id) {
        return userMsgDao.findByUserId(id);
    }
}
