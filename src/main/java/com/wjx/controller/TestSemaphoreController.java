package com.wjx.controller;

import com.wjx.dto.SemaphoreReqDTO;
import com.wjx.service.SemaphoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestSemaphoreController {
    private final SemaphoreService semaphoreService;

    public TestSemaphoreController(SemaphoreService semaphoreService) {
        this.semaphoreService = semaphoreService;
    }

    @GetMapping("/testsemaphore")
    public ResponseEntity<?> handleScenOptReport() throws InterruptedException {
        semaphoreService.processRequest();

        // 返回响应
        return ResponseEntity.ok("Success");
    }
}
