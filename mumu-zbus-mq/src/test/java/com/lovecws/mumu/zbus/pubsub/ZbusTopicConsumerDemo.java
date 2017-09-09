package com.lovecws.mumu.zbus.pubsub;

import com.lovecws.mumu.zbus.ZbusConfiguration;
import org.junit.Before;
import org.junit.Test;

public class ZbusTopicConsumerDemo {

    @Before
    public void before(){
        //ZbusConfiguration.start();
    }
    @Test
    public void receiveTopicMessage(){
        ZbusTopicConsumer consumer=new ZbusTopicConsumer();
        consumer.receiveTopicMessage();
    }
}
