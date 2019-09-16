package com.qooence.code;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ProviderApp {
    public static void main( String[] args ) throws IOException {
        /*
         *手动加载Spring配置文件
         * 还可以新建 resources/META-INF/spring/ 文件 将dubbo-consumer.xml 放在spring文件夹下
         * 启动时会自己加载Spring配置文件
         */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-provider.xml");
        context.start();
        System.in.read();
    }
}
