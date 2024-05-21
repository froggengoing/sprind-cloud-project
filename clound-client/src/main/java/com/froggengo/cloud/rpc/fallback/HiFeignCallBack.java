package com.froggengo.cloud.rpc.fallback;

import com.froggengo.cloud.model.SysRole;
import com.froggengo.cloud.rpc.HiFeign;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author fly
 * @create 2024-05-21-22:37
 **/
@Service
public class HiFeignCallBack implements HiFeign {
    @Override
    public List<SysRole> hello(Integer userId) {
        System.out.println("fallback");
        return Collections.EMPTY_LIST;
    }
}
