package com.wjx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.Semaphore;

@Service
public class SemaphoreService {
    private final Semaphore semaphore = new Semaphore(1); // 最大并发数为 1

    @Async("threadPoolTaskExecutor")
    public void processRequest() throws InterruptedException {
        semaphore.acquire(); // 尝试获取许可
        try {
            System.out.println(new Date().getTime() + "：新的线程启动了！");
            Thread.sleep(5000L);
            System.out.println(new Date().getTime() + "：线程已结束！");
        } finally {
            semaphore.release(); // 无论任务是否成功完成，都要释放许可
        }
    }
}
