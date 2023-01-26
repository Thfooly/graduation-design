package com.forum.service;

import com.forum.pojo.User;

public interface LoginService {
    User login(String username, String password);

//    Integer userMark(String username);
}
