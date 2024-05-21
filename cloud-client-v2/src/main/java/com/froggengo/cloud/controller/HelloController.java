package com.froggengo.cloud.controller;

import com.froggengo.cloud.model.SysRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author fly
 * @create 2024-05-20-21:30
 **/
@RestController
@RequestMapping("/user")
public class HelloController {


    @GetMapping("/role")
    public List<SysRole> hello(@RequestParam("userId") Integer userId) {
        if (userId == null) {
            return Collections.EMPTY_LIST;
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(userId);
        sysRole.setRoleName("admin");
        sysRole.setRoleDesc("管理员");
        SysRole sysRole2 = new SysRole();
        sysRole2.setId(2);
        sysRole2.setRoleName("user");
        sysRole2.setRoleDesc("普通用户");
        return List.of(sysRole, sysRole2);
    }
}
