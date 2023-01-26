package com.forum.service;

import com.forum.vo.MailResult;

public interface ChangePwdService {

    Boolean updatePwd(String password, String email);
}
