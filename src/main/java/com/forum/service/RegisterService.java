package com.forum.service;

import com.forum.pojo.User;
import org.springframework.stereotype.Service;

public interface RegisterService{

    boolean verifyUsername(String username);

    boolean verifyName(String name);

    void saveUser(String name, String username, String password, String email, String phoneNumber);
}
