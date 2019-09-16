package com.qooence.code;

import com.qooence.code.api.UserApi;
import com.qooence.code.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ConsumerApp {


    public static void main(String[] args){

        /*
        *手动加载Spring配置文件
        * 还可以新建 resources/META-INF/spring/ 文件 将dubbo-consumer.xml 放在spring文件夹下
        * 启动时会自己加载Spring配置文件
        */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        context.start();
        UserApi userApi = context.getBean(UserApi.class);
        System.out.println("user: ==> " + userApi.getUserById(1L));

        List<User> list = userApi.getUserList();
        for (User user : list) {
            System.out.println("list:user ==> " + user);
        }
    }

}
