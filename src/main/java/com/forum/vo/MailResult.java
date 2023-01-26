package com.forum.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * 该类用以对验证结果进行一个封装，并返回创建时间以用来对验证码有效时间进行控制
 */
@Data
@AllArgsConstructor
public class MailResult {

    private String email;
    private String code;
    private Date createTime;
    private String uid;

    public MailResult(){}

    public MailResult(String code, String uid, String email) {
        this.email = email;
        this.code = code;
        this.createTime = new Date();
//        uid = UUID.randomUUID().toString().replace("-", "");
        this.uid = uid;
    }
}
