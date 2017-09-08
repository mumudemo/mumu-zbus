package com.lovecws.mumu.zbus.pubsub;

import org.junit.Test;
import org.zbus.net.http.Message;

public class ZbusTopicProducerDemo {

    @Test
    public void sendTopicMessage(){
        ZbusTopicProducer producer=new ZbusTopicProducer();
        Message message = producer.sendTopicMessage("lovecws");
        System.out.println(message);
    }

    @Test
    public void sendTopicAsyncMessage(){
        ZbusTopicProducer producer=new ZbusTopicProducer();
        producer.sendTopicAsyncMessage("lovecws");
    }
}
