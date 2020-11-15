package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;


class TestJUnit509TimeoutTest {

    @BeforeEach
    @Timeout(5)
    void setUp() throws InterruptedException {
        // fails if execution time exceeds 5 seconds
        // it will pass
        Thread.sleep(100);
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void failsIfExecutionTimeExceeds100Milliseconds() throws InterruptedException {
        // fails if execution time exceeds 100 milliseconds
        // it will fail
        Thread.sleep(200);
    }
}