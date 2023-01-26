package com.forum.controller;

import com.forum.common.enums.ExceptionEnums;
import com.forum.common.exception.RuntimeCommonException;
import com.forum.pojo.User;
import com.forum.service.ChangePwdService;
import com.forum.service.EmailService;
import com.forum.service.LoginService;
import com.forum.service.RegisterService;
import com.forum.vo.MailResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;

@ApiModel("登录模块")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("登录")
    @GetMapping("login")
    public ResponseEntity<ModelAndView> login(String username, String password){
        User user = loginService.login(username, password);
        Integer mark = user.getUserMark();
        if (!user.getId().equals(null)){
            if (mark != 2){
                redisTemplate.opsForSet().add("token","manage" + user.getId());
                return ResponseEntity.ok(
                        new ModelAndView("manage")
                                .addObject("token", "manage" + user.getId()));
            }else {
                redisTemplate.opsForSet().add("token","user" + user.getId());
                return ResponseEntity.ok(
                        new ModelAndView("index")
                        //登录信息存储
                            .addObject("token", "user" + user.getId()));
            }

        }else {
            throw new RuntimeCommonException(ExceptionEnums.LOGIN_FALSE);
        }
    }


}
