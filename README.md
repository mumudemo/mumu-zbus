# mumu-zbus ZBUS = MQ + RPC
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/mumudemo/mumu-zbus/blob/master/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/com.weibo/motan.svg?label=Maven%20Central)](https://github.com/mumudemo/mumu-zbus) 
[![Build Status](https://travis-ci.org/mumudemo/mumu-zbus.svg?branch=master)](https://travis-ci.org/mumudemo/mumu-zbus)
[![codecov](https://codecov.io/gh/mumudemo/mumu-zbus/branch/master/graph/badge.svg)](https://codecov.io/gh/mumudemo/mumu-zbus)
[![OpenTracing-1.0 Badge](https://img.shields.io/badge/OpenTracing--1.0-enabled-blue.svg)](http://opentracing.io)

***ZBUS实现了高速轻量化的MQ，同时具有相当完备的RPC支持，加上各大主流语言高可用接入能力，ZBUS可承担SOA平台的基础构建，简而言之ZBUS = MQ + RPC。***

## 简要说明

**ZBUS主要特性：**

-  高速磁盘MQ，支持单播，组播，广播多种消息模式
-  RPC开箱即用，支持同步异步，动态类代理
-  多语言客户端，Java/.NET/JavaScript/PHP/Python/C++/Go(服务器)
-  一个可执行程序，服务器无依赖，大小~1M（压缩后）
-  无应用故障单点，分布式高可用的内置支持
-  简洁的协议设计，类HTTP头部扩展协议，长短连接，WebSocket支持。

## 架构介绍
zbus=mq+rpc，可以使用zbus来实现mq消息服务和rpc远程调用服务。
![](http://static.oschina.net/uploads/space/2015/1221/154904_inJf_238589.png)  
*图片源自http://static.oschina.net/uploads/space/2015/1221/154904_inJf_238589.png*

### mq消息服务
消息生产者将消息发送到broker（如果是单个broker或者jvmroker，直接发送到broker，如果是ha，则选择一个broker来发送消息），在broker可以存储消息，等待消费者进行消费。
![zbus消息模型](http://note.youdao.com/yws/api/personal/file/538555FFF7EA4FBFA9ED3BC4CF2D912F?method=download&shareKey=6e63462d704eca9e7f83b4345cd4f5ed)
***消息发送***
```
    public Message sendMessage(String message){
        ZbusBroker broker=null;
        try {
            broker = new ZbusBroker(ZbusConfiguration.brokerAddress);
            Producer producer=new Producer(broker,ZbusConfiguration.MQNAME, Protocol.MqMode.MQ);
            producer.createMQ();//如果这个mq不存在  则创建
            Message msg = new Message(message);
            msg.setTopic(ZbusConfiguration.topicName);
            //异步消息发送
            /*producer.sendAsync(msg,new Sync.ResultCallback(){
                @Override
                public void onReturn(Object o) {
                    System.out.println("消息发送成功"+o);
                }
            });*/
            return producer.sendSync(msg);
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
```
***消息接受***
```
    public void receiveMessage(){
        try {
            ZbusBroker broker=new ZbusBroker(ZbusConfiguration.brokerAddress);
            Consumer consumer=new Consumer(broker,ZbusConfiguration.MQNAME, Protocol.MqMode.MQ);
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
```

### rpc远程调用服务
服务端先将服务注册到broker中，然后客户端从broker获取到服务的地址，从而进行调用。底层都是通过netty来进行通信。
![zbus rpc调用流程](http://note.youdao.com/yws/api/personal/file/9F6EBD292E1D4650B9C5E7E67B349B1A?method=download&shareKey=a3853e89a0b9d9a8340ae4e040394508)

***服务注册***
```
  public static void main(String[] args) throws IOException {
        RpcProcessor processor = new RpcProcessor();
        processor.addModule(new HelloworldServiceImpl());

        //Broker broker = new ZbusBroker("127.0.0.1:16666;127.0.0.1:16667");
        Broker broker = new ZbusBroker("127.0.0.1:15555");

        ServiceConfig config = new ServiceConfig();
        config.setConsumerCount(2);
        config.setMq("MyRpc");
        config.setBroker(broker);
        config.setMessageProcessor(processor);
        config.setVerbose(true);

        Service svc = new Service(config);
        svc.start();
    }
```

***服务调用***
```
public static void main(String[] args) throws Exception {
        //Broker broker = new ZbusBroker("127.0.0.1:16666;127.0.0.1:16667");
        Broker broker = new ZbusBroker("127.0.0.1:15555");

        Message.MessageInvoker invoker = new MqInvoker(broker, "MyRpc");

        RpcFactory factory = new RpcFactory(invoker);
        HelloworldService hello = factory.getService(HelloworldService.class);
        hello.sayhello("lovecws");
        broker.close();
    }
```
## 相关阅读  
[MQ、RPC、服务总线zbus](https://www.oschina.net/p/zbus)  
[ZBUS高可用HA介绍](https://my.oschina.net/sbz/blog/548559)  
[zbus oschina 地址](https://git.oschina.net/rushmore/zbus)  
[少帮主oschina博客](https://my.oschina.net/sbz)  

## 联系方式
**以上观点纯属个人看法，如有不同，欢迎指正。  
email:<babymm@aliyun.com>  
github:[https://github.com/babymm](https://github.com/babymm)**