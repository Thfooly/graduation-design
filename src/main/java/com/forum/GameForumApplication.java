package com.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.forum.dao")
public class GameForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameForumApplication.class, args);
    }
}
