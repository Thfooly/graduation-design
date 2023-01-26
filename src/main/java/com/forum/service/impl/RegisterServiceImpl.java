package com.forum.service.impl;

import com.forum.common.enums.ExceptionEnums;
import com.forum.common.exception.RuntimeCommonException;
import com.forum.dao.UserDao;
import com.forum.dao.UserMsgDao;
import com.forum.pojo.User;
import com.forum.pojo.UserMsg;
import com.forum.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired(required = false)
    private UserDao userDao;
    @Autowired(required = false)
    private UserMsgDao userMsgDao;

    @Override
    public boolean verifyUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);

        List<User> users = userDao.selectByExample(example);
        if (users.size() != 0){
            throw new RuntimeCommonException(ExceptionEnums.USERNAME_CREATED);
        }else {
            return true;
        }
    }

    @Override
    public boolean verifyName(String name) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", name);

        List<User> users = userDao.selectByExample(example);
        if (users.size() != 0){
            throw new RuntimeCommonException(ExceptionEnums.NAME_CREATED);
        }else {
            return true;
        }
    }

    @Override
    public void saveUser(String name, String username, String password, String email, String phoneNumber) {
        User user = new User(null, name, username, password, email, phoneNumber, new Date(), 0,2);
        userDao.insert(user);
    }

    @Override
    public User findByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return userDao.selectOneByExample(example);
    }

}
