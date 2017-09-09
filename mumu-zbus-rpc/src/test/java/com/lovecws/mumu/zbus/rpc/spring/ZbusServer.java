package com.lovecws.mumu.zbus.rpc.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ZbusServer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-zbus-service.xml");
        applicationContext.start();
    }
}
