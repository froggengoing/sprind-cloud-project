package com.froggengo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fly
 * @create 2024-05-19-20:45
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
public class ClientMain {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ClientMain.class);
        app.setApplicationStartup(new BufferingApplicationStartup(1024 * 1024));
        app.run(args);
    }
}
