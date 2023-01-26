package com.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnums {
    MAIL_USED(400, "该邮箱已被注册"),
    MAIL_NOT_FIND(400, "未找到该邮箱"),
    CODE_ERROR(400, "验证码错误"),
    CODE_TIME_OUT(400, "验证码已超时"),
    USERNAME_CREATED(400, "该账号已被注册"),
    NAME_CREATED(400, "该用户名已存在"),
    MAIL_NOT_VERIFY(400, "邮箱未验证"),
    LOGIN_FALSE(400, "登录失败"),
    NOT_LOGIN(400, "用户未登录"),
    USERNAME_NOT_FIND(400, "用户名不存在");
    private int code;
    private String msg;
}
