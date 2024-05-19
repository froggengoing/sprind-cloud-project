package com.froggengo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fly
 * @create 2024-05-18-1:27
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class SpringGatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringGatewayMain.class, args);
    }
}
