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
 * Date : 2015-12-23
 * Time : 오후 6:52
 * To change this template use File | Settings | File and Code Templates.
 */
@Service("Lookup")
@Scope("prototype")
public class Lookup extends RestApiRequestTemplate {

    public Lookup(Map<String,String> reqData){
        super(reqData);
    }

    @Override
    public void requestParamValidation() throws RequestParamException {
    }

    @Override
    public void service() throws ServiceException {


        //그 다음 노드 별로 분기를 타서 해당 상태 점검 한 뒤에 결과 값을 뿌려줌 아래와 같이 말이지..
        this.apiResult.addProperty("status", true);
        this.apiResult.addProperty("data","14.63.163.191:8222");
    }
}
