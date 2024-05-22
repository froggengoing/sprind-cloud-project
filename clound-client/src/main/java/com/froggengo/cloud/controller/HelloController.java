package com.froggengo.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.froggengo.cloud.model.SysRole;
import com.froggengo.cloud.model.SysUser;
import com.froggengo.cloud.rpc.HiFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fly
 * @create 2024-05-19-20:50
 **/
@RestController
public class HelloController {
    @Autowired
    HiFeign hiFeign;

    @Value("${server.port}")
    int port;

    @GetMapping("/hello/{userId}")
    @SentinelResource("hot-hello")
    public SysUser hello(@PathVariable("userId") Integer userId) {
        System.out.println("请求参数：" + userId);
        if (userId == null) {
            return new SysUser();
        }
        // 出发熔断sentinel异常
        if (userId == 112) {
            System.out.println("触发熔断+" + userId);
            throw new RuntimeException("触发熔断");
        }
        List<SysRole> roleList = hiFeign.hello(userId);
        SysUser sysUser = new SysUser();
        sysUser.setId(1);
        sysUser.setUsername("fly");
        sysUser.setPassword("123456");
        sysUser.setRoles(roleList);
        return sysUser;
    }

    @GetMapping("/loadBalance")
    public Integer hello() {
        return port;
    }
}
