package com.forum.controller;

import com.alibaba.fastjson.JSON;
import com.forum.common.Mail;
import com.forum.common.enums.ExceptionEnums;
import com.forum.common.exception.RuntimeCommonException;
import com.forum.dao.UserDao;
import com.forum.pojo.User;
import com.forum.service.EmailService;
import com.forum.service.RegisterService;
import com.forum.vo.MailResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.junit.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

@ApiModel("注册相关模块")
@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 提交表单 保存用户信息
     * @param name
     * @param username
     * @param password
     * @param email
     * @param phoneNumber
     * @return
     */
    @ApiOperation("注册用户")
    @GetMapping("register")
    public ResponseEntity<ModelAndView> registerUser(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "phoneNumber") String phoneNumber) {
        try {
            if (registerService.verifyUsername(username)&&registerService.verifyName(name)){
                registerService.saveUser(name, username, password, email, phoneNumber);
            }
        }catch (Exception e){
            throw e;
        }finally {
            return ResponseEntity.ok(new ModelAndView("login"));
        }
    }
}
