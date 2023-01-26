package com.forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment){
        //通过accepts判断是否处在自己设定的环境当中
//        boolean b = environment.acceptsProfiles("dev", "test");

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.forum"))
                .build();
    }

//    private ApiInfo apiInfo(){
//        new Contact("thfooly", "default", "null");
//        return new ApiInfo("毕业设计接口文档",
//                );
//    }
}
