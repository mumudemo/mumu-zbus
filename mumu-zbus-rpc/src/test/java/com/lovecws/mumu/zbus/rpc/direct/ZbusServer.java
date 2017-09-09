package com.lovecws.mumu.zbus.rpc.direct;

import com.lovecws.mumu.zbus.rpc.service.HelloworldServiceImpl;
import org.zbus.rpc.RpcProcessor;
import org.zbus.rpc.direct.Service;
import org.zbus.rpc.direct.ServiceConfig;

public class ZbusServer {
    public static void main(String[] args) throws Exception {
        //An processor to handle message consumed from remote (zbus/direct client)
        RpcProcessor processor = new RpcProcessor();
        processor.addModule(new HelloworldServiceImpl());

        ServiceConfig config = new ServiceConfig();

        //Id used by client to address service
        config.entryId = "HaDirectRpc";

        config.messageProcessor = processor;
        //TrackServer address list
        //config.trackServerList = "127.0.0.1:16666;127.0.0.1:16667";
        config.trackServerList = "127.0.0.1:15555";

        Service svc = new Service(config);
        svc.start();
    }
}
