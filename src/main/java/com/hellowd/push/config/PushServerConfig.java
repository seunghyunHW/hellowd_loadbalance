package com.hellowd.push.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.net.InetSocketAddress;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2015-12-23
 * Time : 오후 4:35
 * To change this template use File | Settings | File and Code Templates.
 */

@Configuration
@ComponentScan("com.hellowd.push,com.hellowd.push.config,com.hellowd.push.handler")
@PropertySource("classpath:server.properties")
public class PushServerConfig {

    @Value("${boss.thread.count}")
    private int bossThreadCount;

    @Value("${worker.thread.count}")
    private int workerThreadCount;

    @Value("${tcp.port}")
    private int tcpPort;

    @Value("${http.port}")
    private int httpPort;

    @Value("${zookeeper.hostPort}")
    private int zookeeperHostPort;

    @Value("${zookeeper.sessionTimeout}")
    private int zookeeperSessionTimeout;

    @Bean(name="bossThreadCount")
    public int getBossThreadCount(){
        return bossThreadCount;
    }

    @Bean(name="workerThreadCount")
    public int getWorkerThreadCount(){
        return workerThreadCount;
    }

    @Bean(name="httpPort")
    public int getHttpPort(){
        return httpPort;
    }

    @Bean(name="tcpPort")
    public int getTcpPort(){
        return tcpPort;
    }

    @Bean(name="zookeeperHostPort")
    public int getZookeeperHostPort(){
        return zookeeperHostPort;
    }

    @Bean(name="zookeeperSessionTimeout")
    public int getZookeeperSessionTimeout(){
        return zookeeperSessionTimeout;
    }

    @Bean(name="tcpSocketAddress")
    public InetSocketAddress tcpPort(){
        return new InetSocketAddress(tcpPort);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
