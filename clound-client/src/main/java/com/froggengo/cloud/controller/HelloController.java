package com.froggengo.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.froggengo.cloud.model.SysRole;
import com.froggengo.cloud.model.SysUser;
import com.froggengo.cloud.rpc.HiFeign;
import common.Resultbody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author fly
 * @create 2024-05-19-20:50
 **/
@RestController
public class HelloController {
    static String UPLOAD_FOLDER = System.getProperty("user.dir") + "/upload";
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

    @GetMapping("/thread-block")
    public Integer block() {
        System.out.println("thread-block:" + Thread.currentThread().getName());
        try {
            Thread.sleep(10 * 60 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("thread-finished:" + Thread.currentThread().getName());
        return port;
    }

    @PostMapping("/upload")
    public Resultbody<Object> fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return Resultbody.failed("file is empty");
        }

        try {
            // 保存文件到指定路径
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Resultbody.success();
    }
}
