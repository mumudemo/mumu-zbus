package com.lovecws.mumu.zbus.mq;

import org.junit.Test;

public class ZbusConsumerDemo {

    @Test
    public void receiveMessage(){
        ZbusConsumer consumer=new ZbusConsumer();
        consumer.receiveMessage();
    }


    @Test
    public void takeMessage(){
        ZbusConsumer consumer=new ZbusConsumer();
        consumer.takeMessage();
    }
}
