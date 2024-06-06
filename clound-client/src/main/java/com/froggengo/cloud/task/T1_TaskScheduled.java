package com.froggengo.cloud.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.FixedRateTask;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author fly
 * @create 2024-06-06-18:09
 **/
public class T1_TaskScheduled {
    private static final Logger logger = LoggerFactory.getLogger(T1_TaskScheduled.class);
    AtomicLong begin;
    AtomicInteger count = new AtomicInteger();
    AtomicLong lastBegin = new AtomicLong();
    AtomicLong lastEnd = new AtomicLong();

    /**
     * @see ScheduledAnnotationBeanPostProcessor
     * @see ScheduledAnnotationBeanPostProcessor#processScheduledTask(Scheduled, Runnable, Method, Object)
     */
    public static void main(String[] args) {
        //ConcurrentTaskScheduler taskScheduler = new ConcurrentTaskScheduler(Executors.newScheduledThreadPool(2));
        //ConcurrentTaskScheduler taskScheduler = new ConcurrentTaskScheduler(Executors.newFixedThreadPool(2),
        //        Executors.newScheduledThreadPool(2));
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("my-task-scheduler-");
        taskScheduler.initialize();
        T1_TaskScheduled t1 = new T1_TaskScheduled();
        Runnable runnable = () -> {
            t1.run();
        };
        Duration interval = toDuration(5000, TimeUnit.MILLISECONDS);
        Duration dalay = toDuration(1000, TimeUnit.MILLISECONDS);
        FixedRateTask task = new FixedRateTask(runnable, interval, dalay);
        Instant startTime = taskScheduler.getClock().instant().plus(task.getInitialDelayDuration());
        taskScheduler.scheduleAtFixedRate(task.getRunnable(), startTime, task.getIntervalDuration());
        //taskScheduler.scheduleAtFixedRate(task.getRunnable(), startTime, task.getIntervalDuration());

    }

    private static Duration toDuration(long value, TimeUnit timeUnit) {
        try {
            return Duration.of(value, timeUnit.toChronoUnit());
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Unsupported unit " + timeUnit + " for value \"" + value + "\": " + ex.getMessage());
        }
    }

    public void run() {
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
        try {
            Thread.sleep(count.getAndIncrement() % 7 * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        logger.info("task:{},end:{}", Thread.currentThread().getName(), endTime - begin.get());
        lastBegin.set(beginTime);
        lastEnd.set(endTime);
    }
}
