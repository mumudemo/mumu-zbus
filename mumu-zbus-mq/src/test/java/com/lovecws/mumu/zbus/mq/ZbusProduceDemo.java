package com.lovecws.mumu.zbus.mq;

import org.junit.Test;
import org.zbus.net.http.Message;

public class ZbusProduceDemo {

    @Test
    public void sendMessage(){
        ZbusProduce produce=new ZbusProduce();
        Message message = produce.sendMessage("babymm");
        System.out.println(message);
    }

    @Test
    public void sendAsyncMessage(){
        ZbusProduce produce=new ZbusProduce();
        produce.sendAsyncMessage("babymm");
    }
}
