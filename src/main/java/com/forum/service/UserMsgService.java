package com.forum.service;

import com.forum.pojo.UserMsg;
import org.springframework.stereotype.Service;

@Service
public interface UserMsgService {
    UserMsg findByUserId(Integer id);
}
