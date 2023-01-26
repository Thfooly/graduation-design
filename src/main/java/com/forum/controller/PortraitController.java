package com.forum.controller;

import com.forum.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

// todo

@ApiModel("头像上传相关模块")
@RestController
@RequestMapping("portrait")
public class PortraitController {

    /**
     * 头像上传
     * @param file
     * @param request
     * @return
     */
    @ApiOperation("头像上传")
    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //当前用户
        User user = (User) session.getAttribute("user");
        int begin = file.getOriginalFilename().indexOf(".");
        int last = file.getOriginalFilename().length();
        //获得文件后缀名
        String suffix = file.getOriginalFilename().substring(begin, last);
        String fileName = user.getUsername() + suffix;
        String filePath = "E:\\java_code\\graduation picture\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest); //存储！
            //数据库存放的地址，存的是项目相对路径，这个看你们自己
//            User user1=new User(user.getAccount(),"../static/image/"+fileName,1);
//            userService.updateUserHeadImage(user1);

        }catch (IOException e) {
        }
        return "userInfo";
    }
}
