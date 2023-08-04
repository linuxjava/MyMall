package com.xiao.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//加上下面注解，在浏览器中访问出现如下错误：
//Whitelabel Error Page This application has no explicit mapping for /error, so you are seeing this as a fallback.
//@ComponentScan("com.xiao.mall.config")
public class MyMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMallApplication.class, args);
    }

}
