package com.lovecws.mumu.zbus.pubsub;

import org.junit.Test;

public class ZbusTopicConsumerDemo {

    @Test
    public void receiveTopicMessage(){
        ZbusTopicConsumer consumer=new ZbusTopicConsumer();
        consumer.receiveTopicMessage();
    }
}
