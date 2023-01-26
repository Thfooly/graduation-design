package com.forum.controller;

import com.alibaba.fastjson.JSON;
import com.forum.common.enums.ExceptionEnums;
import com.forum.common.exception.RuntimeCommonException;
import com.forum.service.EmailService;
import com.forum.vo.MailResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("email")
public class MailController {
    @Autowired
    private EmailService emailService;

    /**
     * 根据邮箱发送邮件
     * @param email
     * @return
     */
    @ApiOperation("根据邮箱发送邮件")
    @GetMapping("send")
    public void sendEmail(@ApiParam("邮箱")
                                            @RequestParam(value = "email")
                                            String email){
        if (emailService.verifyEmail(email)){
            // 失败情况 账号已存在
            throw new RuntimeCommonException(ExceptionEnums.MAIL_USED);
        }
        MailResult mr = emailService.sendEmail(email);
//        return ResponseEntity.ok(null);
    }

    @GetMapping("verifycode")
    @ApiOperation("验证码校验")
    public ResponseEntity<ModelAndView> verifyCode(String uuid, String code){
        emailService.verifyEmail(uuid, code);
        return ResponseEntity.ok(new ModelAndView("verifyemailok"));
    }
}
