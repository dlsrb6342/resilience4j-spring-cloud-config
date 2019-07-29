package com.example.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;

@RestController
public class TestController {

    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;
    private final RateLimiterRegistry rateLimiterRegistry;
    private final BulkheadRegistry bulkheadRegistry;
    private final ThreadPoolBulkheadRegistry threadPoolBulkheadRegistry;

    public TestController(CircuitBreakerRegistry circuitBreakerRegistry,
                          RetryRegistry retryRegistry,
                          RateLimiterRegistry rateLimiterRegistry,
                          BulkheadRegistry bulkheadRegistry,
                          ThreadPoolBulkheadRegistry threadPoolBulkheadRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.retryRegistry = retryRegistry;
        this.rateLimiterRegistry = rateLimiterRegistry;
        this.bulkheadRegistry = bulkheadRegistry;
        this.threadPoolBulkheadRegistry = threadPoolBulkheadRegistry;
    }

    @GetMapping("/test")
    public void test() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("test");
        Retry retry = retryRegistry.retry("test");
        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("test");
        Bulkhead bulkhead = bulkheadRegistry.bulkhead("test");
        ThreadPoolBulkhead threadPoolBulkhead = threadPoolBulkheadRegistry.bulkhead("test");
    }
}