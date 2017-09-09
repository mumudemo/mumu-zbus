package com.lovecws.mumu.zbus.jmh;

import com.lovecws.mumu.zbus.ZbusConfiguration;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.zbus.broker.Broker;
import org.zbus.broker.ZbusBroker;
import org.zbus.mq.Producer;
import org.zbus.mq.Protocol;
import org.zbus.net.http.Message;

import java.io.IOException;

public class ZbusProcedureBenchmark {

    private static final byte[] MESSAGE_BYTES=new byte[10];
    private static Broker broker=null;
    private static Producer producer=null;
    static {
        try {
            broker = new ZbusBroker(ZbusConfiguration.brokerAddress);
            producer=new Producer(broker,ZbusConfiguration.MQNAME, Protocol.MqMode.MQ);
            producer.createMQ();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void sendMessage(){
        try {
            Message msg = new Message(new String(MESSAGE_BYTES));
            //msg.setTopic(ZbusConfiguration.topicName);
            Message message = producer.sendSync(msg);
            //producer.sendAsync(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(ZbusProcedureBenchmark.class.getSimpleName())
                .warmupIterations(600)
                .measurementIterations(600)
                .forks(1)
                .threads(20)
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
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
