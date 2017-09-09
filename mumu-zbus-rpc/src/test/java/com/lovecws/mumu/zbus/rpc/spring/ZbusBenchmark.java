package com.lovecws.mumu.zbus.rpc.spring;

import com.lovecws.mumu.zbus.rpc.service.HelloworldService;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.util.Optional;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ZbusBenchmark {

    private static ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-zbus-client.xml");
    private static HelloworldService helloworldService;
    private static byte[] RPC_MESSAGE=new byte[10];
    static {
        applicationContext.start();
        helloworldService = applicationContext.getBean(HelloworldService.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void call(){
        helloworldService.sayhello(new String(RPC_MESSAGE));
    }

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(ZbusBenchmark.class.getSimpleName())
                .warmupIterations(60)
                .measurementIterations(60)
                .forks(1)
                .threads(40)
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}
