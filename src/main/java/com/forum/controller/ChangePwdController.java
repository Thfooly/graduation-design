package com.forum.controller;

import com.alibaba.fastjson.JSON;
import com.forum.common.enums.ExceptionEnums;
import com.forum.common.exception.RuntimeCommonException;
import com.forum.dao.UserDao;
import com.forum.pojo.User;
import com.forum.service.ChangePwdService;
import com.forum.service.EmailService;
import com.forum.service.RegisterService;
import com.forum.vo.MailResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@ApiModel("修改密码模块")
@RestController
@RequestMapping("pwd")
public class ChangePwdController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ChangePwdService changePwdService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 修改密码
     * @param username
     * @return
     */
    @ApiOperation("验证身份")
    @GetMapping("mail")
    public ResponseEntity<String> changePasswordVerifyUser(String username) {
        User user = new User();
        try {
            user = registerService.findByUsername(username);
        }catch (Exception e){
            throw new RuntimeCommonException(ExceptionEnums.USERNAME_NOT_FIND);
        }
        MailResult mr = emailService.sendEmail(user.getEmail());
        redisTemplate.opsForValue().set(user.getId() + "_code", JSON.toJSONString(mr));
        return ResponseEntity.ok(null);
    }

    /**
     * 校验验证码
     * @param emailCode
     * @return
     */
    @ApiOperation("校验验证码")
    @GetMapping("verify")
    public ResponseEntity<ModelAndView> verifyCode(
            @ApiParam("验证码")
            @RequestParam(value = "emailCode") String emailCode,
            String username) {
        User user = registerService.findByUsername(username);
        String s = (String)redisTemplate.opsForValue().get(user.getId() + "_code");
        MailResult mr = JSON.parseObject(s, MailResult.class);
        if ((new Date().getTime() - mr.getCreateTime().getTime() > 10*60*1000)){
            redisTemplate.delete(user.getId() + "_code");
            throw new RuntimeCommonException(ExceptionEnums.CODE_TIME_OUT);
        }
        if (mr.getCode() == emailCode){
            redisTemplate.delete(user.getId() + "_code");
            return ResponseEntity.ok(new ModelAndView().addObject("verifyCode", true));
        }else {
            redisTemplate.delete(user.getId() + "_code");
            return ResponseEntity.ok(new ModelAndView().addObject("verifyCode", false));
        }
    }

    /**
     * 修改密码
     * @return
     */
    @ApiOperation("修改密码")
    @PutMapping
    public ResponseEntity<ModelAndView> updatePwd(String password, Integer id) {
        changePwdService.updatePwd(password, id);
        return ResponseEntity.ok(new ModelAndView("login"));
    }
}
