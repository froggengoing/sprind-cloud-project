package com.froggengo.cloud.servlet;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;

/**
 * 此接口的实现将收到 Web 应用程序中活动会话列表的更改通知。若要接收通知事件，必须在 Web 应用程序的部署描述符中配置实现类。
 *
 * @author fly
 * @create 2024-05-30-17:29
 **/
@WebListener
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(jakarta.servlet.http.HttpSessionEvent se) {
        String contextPath = se.getSession().getServletContext().getContextPath();
        System.out.println("sessionCreated:" + contextPath);
    }

    public void sessionDestroyed(jakarta.servlet.http.HttpSessionEvent se) {
        String contextPath = se.getSession().getServletContext().getContextPath();
        System.out.println("  sessionDestroyed:" + contextPath);
    }


}
