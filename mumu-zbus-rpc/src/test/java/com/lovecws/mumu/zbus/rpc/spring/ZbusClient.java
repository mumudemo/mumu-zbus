package com.lovecws.mumu.zbus.rpc.spring;

import com.lovecws.mumu.zbus.rpc.service.HelloworldService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ZbusClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-zbus-client.xml");
        applicationContext.start();
        HelloworldService helloworldService = applicationContext.getBean(HelloworldService.class);
        helloworldService.sayhello("lovecws");
        applicationContext.close();
    }
}
