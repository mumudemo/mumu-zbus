package com.lovecws.mumu.zbus.mq;

import com.lovecws.mumu.zbus.ZbusConfiguration;
import org.junit.Before;
import org.junit.Test;

public class ZbusConsumerDemo {

    @Before
    public void before(){
        ZbusConfiguration.start();
    }

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
