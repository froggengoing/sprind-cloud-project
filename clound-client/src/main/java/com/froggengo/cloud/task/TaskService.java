package com.froggengo.cloud.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author fly
 * @create 2024-06-02-23:14
 **/
@Component
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    AtomicLong begin;
    AtomicInteger count = new AtomicInteger();
    AtomicLong lastBegin = new AtomicLong();
    AtomicLong lastEnd = new AtomicLong();


    /**
     * 如果任务超时，则下一次为满足cron公式的最近一次执行时间
     *
     * @throws InterruptedException
     */
    //@Scheduled(cron = "*/5 * * * * ?")
    public void task() throws InterruptedException {
        logger.info("task:{},,begin:{}", Thread.currentThread().getName(), System.currentTimeMillis() - begin.get());
        Thread.sleep(9 * 1000);
        logger.info("task:{},end:{}", Thread.currentThread().getName(), System.currentTimeMillis() - begin.get());
    }

    /**
     * 如果某一次，执行时间 > fixedRate ,则 执行完任务后马上执行，但后续的执行时间仍满足：
     * 第n次，执行时间为，start-time + （n-1）*fixedRate
     *
     * @throws InterruptedException
     */
    @Scheduled(fixedRate = 5000)
    public void task1() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        if (begin == null) {
            begin = new AtomicLong(beginTime);
        }
        logger.info("task:{},,begin:{}, n:{} ,diff last begin:{},diff last end:{}",
                Thread.currentThread().getName(),
                beginTime - begin.get(),
                (beginTime - begin.get()) / (Math.max(count.get(), 1) * 5000d),
                beginTime - lastBegin.get(),
                beginTime - lastEnd.get());
        Thread.sleep(count.get() % 7 * 1000L);
        long endTime = System.currentTimeMillis();
        logger.info("task:{},end:{}", Thread.currentThread().getName(), endTime - begin.get());
        lastBegin.set(beginTime);
        lastEnd.set(endTime);
    }

    //@Scheduled(fixedDelay = 5000)
    public void task2() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        if (begin == null) {
            begin = new AtomicLong(beginTime);
        }
        logger.info("task:{},,begin:{}, n:{} ,diff last begin:{},diff last end:{}",
                Thread.currentThread().getName(),
                beginTime - begin.get(),
                (beginTime - begin.get()) / (count.incrementAndGet() * 1.0d),
                beginTime - lastBegin.get(),
                beginTime - lastEnd.get());
        Thread.sleep(count.get() % 7 * 1000L);
        long endTime = System.currentTimeMillis();
        logger.info("task:{},end:{}", Thread.currentThread().getName(), endTime - begin.get());
        lastBegin.set(beginTime);
        lastEnd.set(endTime);
    }
}
