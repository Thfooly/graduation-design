package com.forum.service.impl;

import com.alibaba.fastjson.JSON;
import com.forum.common.Mail;
import com.forum.common.enums.ExceptionEnums;
import com.forum.common.exception.RuntimeCommonException;
import com.forum.dao.UserDao;
import com.forum.pojo.User;
import com.forum.service.EmailService;
import com.forum.vo.MailResult;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@Service
//@Transactional
public class EmailServiceImpl implements EmailService {
    @Autowired
    private Mail mail;
    @Autowired(required = false)
    private UserDao userDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public MailResult sendEmail(String email) {
        MailResult mailResult = new MailResult();
        try {
            mailResult = mail.sendMail(email);
//            System.out.println(mailResult.toString());//todo
            redisTemplate.opsForValue().set(mailResult.getUid(), JSON.toJSONString(mailResult));
            //System.out.println(redisTemplate.keys("*"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mailResult;
    }

    @Override
    public Boolean verifyEmail(String uuid, String code) {

        String s = (String)redisTemplate.opsForValue().get(uuid);
        MailResult mr = JSON.parseObject(s, MailResult.class);
        if ((new Date().getTime() - mr.getCreateTime().getTime() < 10*60*1000)){
            redisTemplate.delete(uuid);
            throw new RuntimeCommonException(ExceptionEnums.CODE_TIME_OUT);
        }
        else if (!mr.getCode().equals(code)){
            throw new RuntimeCommonException(ExceptionEnums.CODE_ERROR);
        }else {
            Example example = new Example(User.class);
            example.createCriteria().andEqualTo("email", mr.getEmail());

            User u = new User();
            u.setEmailVerify(1);
            int updateByExample = userDao.updateByExample(u, example);
            redisTemplate.delete(uuid);
            return true;
        }
    }

    @Override
    public Boolean verifyEmail(String email) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email);

        List<User> users = userDao.selectByExample(example);
        if (users.size() == 0){
            return true;
        }
        return false;
    }
}
