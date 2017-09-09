package com.lovecws.mumu.zbus.rpc.direct;

import com.lovecws.mumu.zbus.rpc.service.HelloworldService;
import org.zbus.broker.Broker;
import org.zbus.broker.ZbusBroker;
import org.zbus.rpc.RpcFactory;
import org.zbus.rpc.direct.HaInvoker;

public class ZbusClient {

    public static void main(String[] args) throws Exception {
        //Broker broker = new ZbusBroker("[127.0.0.1:16666;127.0.0.1:16667]");
        Broker broker = new ZbusBroker("127.0.0.1:15555");

        //HA 模式invoke
        HaInvoker invoker = new HaInvoker(broker, "HaDirectRpc");

        RpcFactory factory = new RpcFactory(invoker);
        HelloworldService hello = factory.getService(HelloworldService.class);
        System.out.println( hello.sayhello("lovecws"));
        broker.close();
    }

}
