package com.lovecws.mumu.zbus.mq;

import com.lovecws.mumu.zbus.ZbusConfiguration;
import org.zbus.broker.ZbusBroker;
import org.zbus.mq.Consumer;
import org.zbus.mq.Protocol;
import org.zbus.net.http.Message;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 消费者 消费消息
 */
public class ZbusConsumer {

    /**
     * 接受消息
     */
    public void receiveMessage(){
        try {
            ZbusBroker broker=new ZbusBroker(ZbusConfiguration.brokerAddress);
            Consumer consumer=new Consumer(broker,ZbusConfiguration.mqName, Protocol.MqMode.MQ);
            consumer.createMQ();//创建mq
            consumer.start();
            consumer.onMessage(new Consumer.ConsumerHandler() {
                @Override
                public void handle(Message msg, Consumer consumer) throws IOException {
                    System.out.println(new Date().toLocaleString()+" "+msg);
                }
            });
            TimeUnit.SECONDS.sleep(10);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自己获取消息
     */
    public void takeMessage(){
        try {
            ZbusBroker broker=new ZbusBroker(ZbusConfiguration.brokerAddress);
            Consumer consumer=new Consumer(broker,ZbusConfiguration.mqName, Protocol.MqMode.MQ);
            consumer.createMQ();//创建mq
            consumer.start();
            while (true){
                try {
                    Message message = consumer.take(1000);
                    System.out.println(message);
                    if(message==null){
                        break;
                    }
                }catch (Exception e){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
