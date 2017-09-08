package com.lovecws.mumu.zbus.pubsub;

import com.lovecws.mumu.zbus.ZbusConfiguration;
import org.zbus.broker.Broker;
import org.zbus.broker.BrokerConfig;
import org.zbus.broker.SingleBroker;
import org.zbus.mq.Consumer;
import org.zbus.mq.MqConfig;
import org.zbus.mq.Protocol;
import org.zbus.net.http.Message;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ZbusTopicConsumer {

    /**
     * 接受订阅消息
     */
    public void receiveTopicMessage(){
        // 1）创建Broker代表
        Broker broker =null;
        BrokerConfig brokerConfig = new BrokerConfig();
        try {
            brokerConfig.setBrokerAddress(ZbusConfiguration.brokerAddress);
            broker = new SingleBroker(brokerConfig);

            MqConfig config = new MqConfig();
            config.setBroker(broker);
            config.setMq(ZbusConfiguration.mqName);
            config.setTopic(ZbusConfiguration.topicName);
            config.setMode(Protocol.MqMode.PubSub);

            // 2) 创建消费者
            Consumer c = new Consumer(config);

            c.onMessage(new Consumer.ConsumerHandler() {
                @Override
                public void handle(Message msg, Consumer consumer) throws IOException {
                    System.out.println(msg);
                }
            });
            TimeUnit.SECONDS.sleep(10);
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
