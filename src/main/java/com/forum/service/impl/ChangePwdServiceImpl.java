package com.forum.service.impl;

import com.forum.dao.UserDao;
import com.forum.pojo.User;
import com.forum.service.ChangePwdService;
import com.forum.service.RegisterService;
import com.forum.vo.MailResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
public class ChangePwdServiceImpl implements ChangePwdService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public Boolean updatePwd(String password, String email) {
        User user = userDao.findByEmail(email);
        user.setEmail(email);
        return userDao.update(user);
    }
}
