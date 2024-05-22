package com.froggengo.cloud.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;

@Configuration
public class SentinelExceptionHandler implements BlockExceptionHandler {

    /**
     * 这里处理sentinel的异常优先级高于@RestControllerAdvice中定义的全局异常
     * @param request  Servlet request
     * @param response Servlet response
     * @param throwable        the block exception
     * @throws Exception
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException throwable) throws Exception {
        System.out.println("触发sentinel异常");
        JSONObject resultObj = new JSONObject();
        if (throwable instanceof FlowException) {
            resultObj.put("code", 100);
            resultObj.put("msg", "接口限流");
        }
        if (throwable instanceof DegradeException) {
            resultObj.put("code", 101);
            resultObj.put("msg", "服务降级");
        }
        if (throwable instanceof ParamFlowException) {
            resultObj.put("code", 102);
            resultObj.put("msg", "热点参数限流");
        }
        if (throwable instanceof SystemBlockException) {
            resultObj.put("code", 103);
            resultObj.put("msg", "触发系统保护规则");
        }
        if (throwable instanceof AuthorityException) {
            resultObj.put("code", 104);
            resultObj.put("msg", "授权规则不通过");
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(resultObj.toString());
        writer.flush();
        writer.close();
    }
}

