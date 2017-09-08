package com.lovecws.mumu.zbus;

import org.zbus.mq.server.MqServer;
import org.zbus.mq.server.MqServerConfig;

public class ZbusConfiguration {

    public static String brokerAddress="localhost:15555";
    public static String mqName="babymm";
    public static String topicName="babymmTopic";

    static {
        MqServerConfig config = new MqServerConfig();
        config.serverPort = 15555;
        config.storePath = "./store";
        final MqServer server = new MqServer(config);
        try {
            server.start();
            System.out.println("zbus服务启动....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
