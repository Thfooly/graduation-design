package com.forum.service;

import com.forum.vo.MailResult;

public interface EmailService {
    MailResult sendEmail(String email);

    Boolean verifyEmail(String email);

    Boolean verifyEmail(String uuid, String code);
}
