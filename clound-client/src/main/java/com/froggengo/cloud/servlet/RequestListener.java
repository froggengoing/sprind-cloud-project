package com.froggengo.cloud.servlet;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author fly
 * @create 2024-05-30-17:29
 **/
@WebListener
public class RequestListener implements ServletRequestListener {

    /**
     * 请求被定义为在即将进入每个 Web 应用程序中的第一个 Servlet 或过滤器时进入范围，
     * 当它退出链中的最后一个 Servlet 或第一个过滤器时，请求被定义为超出范围。
     *
     * @param sre Information about the request
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = ((HttpServletRequest) sre.getServletRequest());
        System.out.println("requestInitialized:" + request.getRequestURI());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = ((HttpServletRequest) sre.getServletRequest());
        System.out.println("  requestDestroyed:" + request.getRequestURI());
    }


}
