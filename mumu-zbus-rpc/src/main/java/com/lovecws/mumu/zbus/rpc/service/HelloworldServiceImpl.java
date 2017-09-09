package com.lovecws.mumu.zbus.rpc.service;

public class HelloworldServiceImpl implements HelloworldService{

    @Override
    public String sayhello(String hello) {
        System.out.println(""+hello);
        return hello;
    }
}
