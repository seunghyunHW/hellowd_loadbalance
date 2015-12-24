package com.hellowd.push.service;

import com.hellowd.push.PushServer;
import com.hellowd.push.api.RestApiRequestTemplate;
import com.hellowd.push.api.exception.RequestParamException;
import com.hellowd.push.api.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2015-12-24
 * Time : 오후 12:06
 * To change this template use File | Settings | File and Code Templates.
 */

@Service("ServerMonitoring")
@Scope("prototype")
public class ServerMonitoring  extends RestApiRequestTemplate {

    @Autowired
    private PushServer pushServer;

    public ServerMonitoring(Map<String,String> reqData){
        super(reqData);
    }

    @Override
    public void requestParamValidation() throws RequestParamException {
    }

    @Override
    public void service() throws ServiceException {

        this.apiResult.add("os", pushServer.getServerMonitor().getOsInfo());
        this.apiResult.add("memory", pushServer.getServerMonitor().getMemoryInfo());
        this.apiResult.addProperty("uptime", pushServer.getServerMonitor().getUptime());
        this.apiResult.addProperty("requests", pushServer.getServerMonitor().getRequest());
        this.apiResult.addProperty("recvedBytes", pushServer.getServerMonitor().getReceivedBytes());
        this.apiResult.addProperty("sendBytes", pushServer.getServerMonitor().getSendBytes());
        this.apiResult.addProperty("errors", pushServer.getServerMonitor().getErrorCount());
        this.apiResult.addProperty("connections", pushServer.getServerMonitor().getConnectionCount());
        this.apiResult.addProperty("currentProcessTime", pushServer.getServerMonitor().getCurrentProcessTime());
        this.apiResult.addProperty("averageProcessTime", pushServer.getServerMonitor().getAverageProcessTime());

    }
}

