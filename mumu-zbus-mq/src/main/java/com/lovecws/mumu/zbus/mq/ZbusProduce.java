package com.lovecws.mumu.zbus.mq;

import com.lovecws.mumu.zbus.ZbusConfiguration;
import org.zbus.broker.ZbusBroker;
import org.zbus.mq.Producer;
import org.zbus.mq.Protocol;
import org.zbus.net.Sync;
import org.zbus.net.http.Message;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ZbusProduce {

    /**
     * 发送同步消息
     * @param message 消息内容
     */
    public Message sendMessage(String message){
        ZbusBroker broker=null;
        try {
            broker = new ZbusBroker(ZbusConfiguration.brokerAddress);
            Producer producer=new Producer(broker,ZbusConfiguration.mqName, Protocol.MqMode.MQ);
            producer.createMQ();//如果这个mq不存在  则创建
            Message message1 = producer.sendSync(new Message(message));
            return message1;
        } catch (IOException |InterruptedException  e) {
            e.printStackTrace();
        }finally {
            try {
                broker.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发送异步消息
     * @param message
     */
    public void sendAsyncMessage(String message){
        CountDownLatch latch=new CountDownLatch(1);
        ZbusBroker broker=null;
        try {
            broker = new ZbusBroker(ZbusConfiguration.brokerAddress);
            Producer producer=new Producer(broker,ZbusConfiguration.mqName, Protocol.MqMode.MQ);
            producer.createMQ();//如果这个mq不存在  则创建
            producer.sendAsync(new Message(message),new Sync.ResultCallback(){
                @Override
                public void onReturn(Object o) {
                    System.out.println("消息发送成功"+o);
                    latch.countDown();
                }
            });
            latch.await();
        } catch (IOException |InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                broker.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
