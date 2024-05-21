package com.froggengo.cloud.rpc;

import com.froggengo.cloud.model.SysRole;
import com.froggengo.cloud.rpc.fallback.HiFeignCallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author fly
 * @create 2024-05-21-0:02
 **/
@FeignClient(value = "cloud-client-v2", fallback = HiFeignCallBack.class)
@Component
public interface HiFeign {

    @GetMapping("/user/role")
    List<SysRole> hello(@RequestParam("userId") Integer userId);
}
