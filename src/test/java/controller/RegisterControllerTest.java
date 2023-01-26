package controller;


import com.forum.GameForumApplication;
import com.forum.controller.RegisterController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = GameForumApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class RegisterControllerTest {

    @Autowired
    private RegisterController registerController;

    @Test
    public void test(){
//        registerController.sendEmail("2089173557@qq.com");
    }
}
