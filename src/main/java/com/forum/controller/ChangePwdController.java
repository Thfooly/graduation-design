package com.forum.controller;

import com.forum.common.enums.ExceptionEnums;
import com.forum.common.exception.RuntimeCommonException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ApiModel("修改密码模块")
@RestController
@RequestMapping("/pwd")
public class ChangePwdController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ChangePwdService changePwdService;

    /**
     * 修改密码
     * @param username
     * @return
     */
    @ApiOperation("验证身份")
    @PostMapping("user")
    public String changePassword(String username) {
        User user = registerService.findByUsername(username);
        if (user.getEmail().isEmpty()) {
            throw new RuntimeCommonException(ExceptionEnums.USERNAME_NOT_FIND);
        } else {
            MailResult mr = emailService.sendEmail(user.getEmail());
            return "verityPwd";
        }
    }

    /**
     * 校验验证码
     * @param emailCode
     * @param uuid
     * @param model
     * @return
     */
    @ApiOperation("校验验证码")
    @GetMapping("verify")
    public String verifyCode(
            @ApiParam("验证码")
            @RequestParam(value = "emailCode") String emailCode,
            @ApiParam("UUID")
            @RequestParam(value = "UUID") String uuid,
            Model model) {

        Integer i = emailService.verifyEmail(emailCode, uuid);
        if (i == 2) {
//            model.addAttribute("verifyMsg", "验证码错误");
//            model.addAttribute("verifyCode", false);
//            return "error";
            throw new RuntimeCommonException(ExceptionEnums.CODE_ERROR);
        } else if (i == 3) {
//            model.addAttribute("verifyMsg", "请求超时,请重新发送验证码");
//            model.addAttribute("verifyCode", false);
//            return "error";
            throw new RuntimeCommonException(ExceptionEnums.CODE_TIME_OUT);
        } else {
//            model.addAttribute("verifyMsg", "校验成功");
//            model.addAttribute("verifyCode", true);
            return "changePwd";
        }
    }

    /**
     * 重新发送邮件
     * @param model
     * @param email
     * @return
     */
    @ApiOperation("重新发送邮件")
    @GetMapping("reMail")
    public ResponseEntity<String > reMail(Model model, String email) {
        MailResult mr = emailService.sendEmail(email);
        if (mr.getUid().isEmpty()) {
//            model.addAttribute("error", "未找到该邮箱");
//            return "error";
            throw new RuntimeCommonException(ExceptionEnums.MAIL_NOT_FIND);
        } else {
            return ResponseEntity.ok(mr.getUid());
        }
    }

    /**
     * 修改密码
     * @param password
     * @param email
     * @return
     */
    @ApiOperation("修改密码")
    @PutMapping("updatePwd")
    public String updatePwd(String password, String email) {
        changePwdService.updatePwd(password, email);
        return "login";
    }
}
