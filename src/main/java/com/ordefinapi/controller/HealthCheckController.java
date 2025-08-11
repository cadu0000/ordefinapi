package com.ordefinapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/health")
    public String healthCheck() {
        return "Ordefin API is up and running! Timestamp: " + System.currentTimeMillis();
    }
}
