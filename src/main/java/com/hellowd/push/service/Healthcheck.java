package com.hellowd.push.service;

import com.hellowd.push.api.RestApiRequestTemplate;
import com.hellowd.push.api.exception.RequestParamException;
import com.hellowd.push.api.exception.ServiceException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2015-12-24
 * Time : 오후 2:53
 * To change this template use File | Settings | File and Code Templates.
 */
@Service("Healthcheck")
@Scope("prototype")
public class Healthcheck extends RestApiRequestTemplate {

    public Healthcheck(Map<String,String> reqData){
        super(reqData);
    }

    @Override
    public void requestParamValidation() throws RequestParamException {
    }

    @Override
    public void service() throws ServiceException {
        this.apiResult.addProperty("resultCode", "200");
    }
}