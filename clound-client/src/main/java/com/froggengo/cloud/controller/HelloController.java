package com.froggengo.cloud.controller;

import com.froggengo.cloud.model.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fly
 * @create 2024-05-19-20:50
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public SysUser hello() {
        SysUser sysUser = new SysUser();
        sysUser.setId(1);
        sysUser.setUsername("fly");
        sysUser.setPassword("123456");
        return sysUser;
    }
}
