package com.hellowd.push;

import com.hellowd.push.config.PushServerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2015-12-23
 * Time : 오후 4:30
 * HWServer는 PushServer를 구동하기 전에 config 파일을 받아서 반영
 */
public class HWMainServer {

    public static void main(String[] args) {

        AbstractApplicationContext springContext = null;

        try{

            springContext = new AnnotationConfigApplicationContext(PushServerConfig.class);
            springContext.registerShutdownHook();

            PushServer server = springContext.getBean(PushServer.class);
            server.start();

        }finally {

            springContext.close();

        }
    }
}
