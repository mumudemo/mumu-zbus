package com.lovecws.mumu.zbus.pubsub;

import com.lovecws.mumu.zbus.ZbusConfiguration;
import org.zbus.broker.Broker;
import org.zbus.broker.BrokerConfig;
import org.zbus.broker.SingleBroker;
import org.zbus.broker.ZbusBroker;
import org.zbus.mq.Producer;
import org.zbus.mq.Protocol;
import org.zbus.net.Sync;
import org.zbus.net.http.Message;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ZbusTopicProducer {

    /**
     * 发送广播消息
     * @param message
     */
    public Message sendTopicMessage(String message){
        Broker broker = null;
        try {
            BrokerConfig brokerConfig = new BrokerConfig();
            brokerConfig.setBrokerAddress(ZbusConfiguration.brokerAddress);
            broker = new SingleBroker(brokerConfig);
            Producer producer = new Producer(broker, ZbusConfiguration.mqName, Protocol.MqMode.PubSub);
            producer.createMQ();

            Message msg = new Message();
            msg.setTopic(ZbusConfiguration.topicName);
            msg.setBody(message);

            return producer.sendSync(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
     * 发送广播异步消息
     * @param message
     */
    public void sendTopicAsyncMessage(String message){
        //1）创建Broker代表
        BrokerConfig config = new BrokerConfig();
        config.setBrokerAddress(ZbusConfiguration.brokerAddress);
        Broker broker = null;
        CountDownLatch latch=new CountDownLatch(1);
        try {
            broker = new SingleBroker(config);
            //2) 创建生产者
            Producer p = new Producer(broker, ZbusConfiguration.mqName, Protocol.MqMode.PubSub);
            p.createMQ();

            Message msg = new Message();
            msg.setTopic(ZbusConfiguration.topicName);
            msg.setBody(message);

            p.sendAsync(msg, new Sync.ResultCallback<Message>() {
                @Override
                public void onReturn(Message message) {
                    System.out.println(message);
                    latch.countDown();
                }
            });
            latch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                broker.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
