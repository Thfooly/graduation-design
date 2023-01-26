package com.forum.common;

import com.forum.utils.RandomUtils;
import com.forum.vo.MailResult;
import com.sun.deploy.net.HttpResponse;
import org.junit.Test;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import java.util.UUID;

/**
 * 发送邮件的工具类
 * 接收对应的邮箱
 * 返回验证码和生成验证码时间
 */
@Component
public class Mail {
    public MailResult sendMail(String mail) throws MessagingException {
        // 创建Properties 类用于记录邮箱的一些属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱端口587
        props.put("mail.smtp.port", "587");
        // 此处填写，写信人的账号(自行修改)
        props.put("mail.user", "1978622002@qq.com");
        // 此处填写16位STMP口令(自行修改)
        props.put("mail.password", "fyagirbwbdpgedhi");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人的邮箱 ----这里mail是sendMail(String mail)中的参数  (自行修改)
        InternetAddress to = new InternetAddress(mail);

        message.setRecipient(MimeMessage.RecipientType.TO, to);
        // 设置邮件标题
        message.setSubject("游戏论坛验证码");

        String result = RandomUtils.getRandom();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 设置邮件的内容体
        message.setContent("请点击进行验证:<a href=" +
                "\"http://127.0.0.1:13000/email/verifycode?name=" + uuid +
                "&code=" + result + " \""+
                ">点击此处</a>", "text/html;charset=UTF-8");

//        //存验证码在服务器---可应用与servlet中
//        HttpSession session = req.getSession();
//        session.setAttribute("trueyzm", result);
//        //以秒为单位，即在没有活动30分钟后，session将失效
//        session.setMaxInactiveInterval(30*60);
//        System.out.println("yzm->:" + result);

        // 最后当然就是发送邮件啦
        Transport.send(message);

        return new MailResult(result, uuid, mail);
    }

//    @Test
//    //邮箱测试
//    public void test10() throws MessagingException {
//        Mail mail = new Mail();
//        mail.sendMail("2089173557@qq.com");
//        System.out.println("邮件以发送,请接收...");
//    }

}
