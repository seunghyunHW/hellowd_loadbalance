package com.hellowd.push.api;

import com.google.gson.JsonObject;
import com.hellowd.push.api.exception.RequestParamException;
import com.hellowd.push.api.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2015-12-23
 * Time : 오후 6:40
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class RestApiRequestTemplate implements RestApiRequest {
    protected Logger logger;

    protected Map<String,String> reqData;

    protected JsonObject apiResult;

    public RestApiRequestTemplate(Map<String,String> reqData){
        this.logger = LoggerFactory.getLogger(RestApiRequestTemplate.class);
        this.apiResult = new JsonObject();
        this.reqData = reqData;

        logger.info("request data : " + this.reqData);
    }


    public void executeService(){
        try{
            this.requestParamValidation();
            this.service();
        }catch (RequestParamException e){
            logger.error(e.toString());
            this.apiResult.addProperty("resultCode" , "405");
        }catch(ServiceException e){
            logger.error(e.toString());
            this.apiResult.addProperty("resultCode" , "501");
        }
    }

    public JsonObject getApiResult(){
        return this.apiResult;
    }

}
