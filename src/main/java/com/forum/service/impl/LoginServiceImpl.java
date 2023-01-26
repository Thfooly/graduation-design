package com.forum.service.impl;

import com.forum.common.enums.ExceptionEnums;
import com.forum.common.exception.RuntimeCommonException;
import com.forum.dao.UserDao;
import com.forum.pojo.User;
import com.forum.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired(required = false)
    private UserDao userDao;

    /**
     * 登录判断
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        Example e = new Example(User.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("username", username);

        User user = userDao.selectOneByExample(e);
        if (password.equals(user.getPassword())){
            return user;
        }else {
            throw new RuntimeCommonException(ExceptionEnums.LOGIN_FALSE);
        }
    }

}
