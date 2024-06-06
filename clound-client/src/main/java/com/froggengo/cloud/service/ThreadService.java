package com.froggengo.cloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author fly
 * @create 2024-06-03-5:06
 **/
@Service
public class ThreadService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadService.class);

    @Async
    public void thread() {
        logger.info("thread:{}", Thread.currentThread().getName());
    }
}
