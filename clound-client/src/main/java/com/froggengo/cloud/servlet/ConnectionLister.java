package com.froggengo.cloud.servlet;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * @author fly
 * @create 2024-05-30-17:29
 **/
@WebListener
public class ConnectionLister implements ServletContextListener {
    /**
     * javax.servlet.ServletContextEvent是用于通知Web应用的servlet上下文更改的事件类。
     * 主要就是为了获取更改后的ServletContext进行后续多种操作。
     *
     * @param sce Information about the ServletContext that was initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String contextPath = sce.getServletContext().getContextPath();
        System.out.println("contextInitialized:" + contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String contextPath = sce.getServletContext().getContextPath();
        System.out.println("  contextDestroyed:" + contextPath);
    }
}
