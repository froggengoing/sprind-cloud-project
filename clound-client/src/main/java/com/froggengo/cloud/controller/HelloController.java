package com.froggengo.cloud.controller;

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
    public SysUser hello(@PathVariable("userId") Integer userId) {
        if (userId == null) {
            return new SysUser();
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
