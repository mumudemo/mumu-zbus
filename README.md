# mumu-zbus ZBUS = MQ + RPC
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/mumudemo/mumu-zbus/blob/master/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/com.weibo/motan.svg?label=Maven%20Central)](https://github.com/mumudemo/mumu-zbus) 
[![Build Status](https://travis-ci.org/mumudemo/mumu-zbus.svg?branch=master)](https://travis-ci.org/mumudemo/mumu-zbus)
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

### rpc远程调用服务


## 相关阅读  
[MQ、RPC、服务总线zbus](https://www.oschina.net/p/zbus)  
[ZBUS高可用HA介绍](https://my.oschina.net/sbz/blog/548559)  
[zbus oschina 地址](https://git.oschina.net/rushmore/zbus)
[少帮主oschina博客](https://my.oschina.net/sbz)

## 联系方式
**以上观点纯属个人看法，如有不同，欢迎指正。  
email:<babymm@aliyun.com>  
github:[https://github.com/babymm](https://github.com/babymm)**