package com.froggengo.cloud.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author fly
 * @create 2024-05-22-18:17
 **/
@RestControllerAdvice
public class CustomerExceptionHandler {

    //@ExceptionHandler(value = BlockException.class)
    //public String handleException(BlockException e) {
    //    System.out.println("global sentinel exception");
    //    JSONObject resultObj = new JSONObject();
    //    resultObj.put("code", 100);
    //    resultObj.put("msg", "接口限流");
    //    return resultObj.toString();
    //}

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        System.out.println("global exception：" + e.getMessage());
        //e.printStackTrace();
        JSONObject resultObj = new JSONObject();
        resultObj.put("code", 100);
        resultObj.put("msg", "接口内部异常");
        return resultObj.toString();
    }
}
