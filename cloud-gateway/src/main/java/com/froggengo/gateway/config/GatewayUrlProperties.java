package com.froggengo.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author fly
 * @create 2024-05-20-16:06
 **/
@Configuration
@ConfigurationProperties("sys.url")
public class GatewayUrlProperties {
    private List<String> blackList;
    private List<String> whiteList;

    public List<String> getBlackList() {
        return blackList;
    }

    public GatewayUrlProperties setBlackList(List<String> blackList) {
        this.blackList = blackList;
        return this;
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public GatewayUrlProperties setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
        return this;
    }
}
