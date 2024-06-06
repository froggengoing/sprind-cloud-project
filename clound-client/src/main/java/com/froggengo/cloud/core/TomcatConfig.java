package com.froggengo.cloud.core;

import com.froggengo.cloud.servlet.ConnectionLoggingValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return factory -> {
            factory.addContextValves(new ConnectionLoggingValve());
        };
    }


    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("my-task-scheduler-");
        return scheduler;
    }


    //@Bean("exchange-pool")
    //public ThreadPoolTaskExecutor applicationEventMulticasterThreadPool() {
    //    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    //
    //    taskExecutor.setThreadNamePrefix("exchange-ThreadPool-");
    //
    //    taskExecutor.setCorePoolSize(10);
    //
    //    taskExecutor.setMaxPoolSize(50);
    //
    //    taskExecutor.setQueueCapacity(2000);
    //
    //    taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    //
    //    taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
    //
    //    taskExecutor.initialize();
    //    return taskExecutor;
    //}
}