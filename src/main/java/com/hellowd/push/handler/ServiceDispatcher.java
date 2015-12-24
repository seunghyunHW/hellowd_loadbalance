package com.hellowd.push.handler;

import com.hellowd.push.api.RestApiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2015-12-23
 * Time : 오후 6:19
 * To change this template use File | Settings | File and Code Templates.
 */
@Component
public class ServiceDispatcher {

    static Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);

    private static ApplicationContext springContext;

    @Autowired
    public void init(ApplicationContext springContext){
        ServiceDispatcher.springContext = springContext;
    }

    public static RestApiRequest dispatch(Map<String,String> requestMap){
        String serviceUri = requestMap.get("REQUEST_URI");
        String httpMethod = requestMap.get("REQUEST_METHOD");
        String beanName = null;

        if(serviceUri == null || !httpMethod.equals("GET")){
            beanName = "BadRequest";
        }else{
            if(serviceUri.startsWith("/lookup")){
                beanName = "Lookup";
            }else if(serviceUri.startsWith("/manager/status")){
                beanName = "ServerMonitoring";//"Status";
            }else if(serviceUri.startsWith("/manager/healthcheck")){
                beanName = "Healthcheck";//"HealthCheck";
            }else{
                beanName = "NotFoundRequest";
            }
        }

        RestApiRequest restApiRequest = null;
        try{
            restApiRequest = (RestApiRequest)springContext.getBean(beanName,requestMap);
        }catch (Exception e){
            logger.debug(e.toString());
            restApiRequest = (RestApiRequest)springContext.getBean("BadRequest",requestMap);
        }

        return restApiRequest;
    }
}
