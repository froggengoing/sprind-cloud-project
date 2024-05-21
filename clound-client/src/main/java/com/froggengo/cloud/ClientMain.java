package com.froggengo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fly
 * @create 2024-05-19-20:45
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ClientMain {
    public static void main(String[] args) {
        SpringApplication.run(ClientMain.class, args);
    }
}
