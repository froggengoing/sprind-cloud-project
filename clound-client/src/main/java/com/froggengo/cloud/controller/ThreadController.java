package com.froggengo.cloud.controller;

import com.froggengo.cloud.service.ThreadService;
import common.Resultbody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fly
 * @create 2024-06-03-5:05
 **/
@RestController
public class ThreadController {
    private static final Logger logger = LoggerFactory.getLogger(ThreadController.class);

    @Autowired
    ThreadService threadService;

    @GetMapping("/thread")
    public Resultbody thread() {
        logger.info("thread:{}", Thread.currentThread().getName());
        threadService.thread();
        return Resultbody.success();
    }
}
