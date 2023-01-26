package com.forum.service.impl;

import com.forum.dao.UserDao;
import com.forum.pojo.User;
import com.forum.service.ChangePwdService;
import com.sun.org.apache.xerces.internal.impl.dv.xs.IDDV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
//@Transactional
public class ChangePwdServiceImpl implements ChangePwdService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public Boolean updatePwd(String password, Integer id) {
        User user = userDao.selectByPrimaryKey(id);
        user.setPassword(password);
        userDao.updateByPrimaryKey(user);
        return true;
    }
}
