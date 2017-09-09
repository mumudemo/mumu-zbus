package com.lovecws.mumu.zbus.rpc.mq;

import com.lovecws.mumu.zbus.rpc.service.HelloworldService;
import org.zbus.broker.Broker;
import org.zbus.broker.ZbusBroker;
import org.zbus.net.http.Message;
import org.zbus.rpc.RpcFactory;
import org.zbus.rpc.mq.MqInvoker;

public class ZbusClient {

    public static void main(String[] args) throws Exception {
        //Broker broker = new ZbusBroker("127.0.0.1:16666;127.0.0.1:16667");
        Broker broker = new ZbusBroker("127.0.0.1:15555");

        Message.MessageInvoker invoker = new MqInvoker(broker, "MyRpc");

        RpcFactory factory = new RpcFactory(invoker);
        HelloworldService hello = factory.getService(HelloworldService.class);
        hello.sayhello("lovecws");
        broker.close();
    }
}
