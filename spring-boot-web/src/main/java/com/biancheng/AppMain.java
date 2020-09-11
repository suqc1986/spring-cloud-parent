package com.biancheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.biancheng.enable.EnableUserClient;
import com.biancheng.startcmd.StartCommand;
@EnableAsync//开启异步调用
@EnableUserClient//启动自定义start：注解方式启动无需spring.factories
@SpringBootApplication
public class AppMain {
    public static void main(String[] args) {
    	 // 启动参数设置, 比如自动生成端口
    	new StartCommand(args);
        SpringApplication.run(AppMain.class, args);
    }
}