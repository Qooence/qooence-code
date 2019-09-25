package com.qooence.code.usercenter;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 开启基于注解的dubbo功能（主要是包扫描@DubboComponentScan）
// 也可以在配置文件中使用dubbo.scan.base-package来替代   @EnableDubbo
// 还可以引入配置信息
//@ImportResource(locations="classpath:provider.xml")
@EnableDubbo
@SpringBootApplication
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

}
