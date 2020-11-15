package com.example;


import org.junit.jupiter.api.*;

class TestJUnit501Lifecycle {

    @BeforeAll
    static void beforeClass() {
        System.out.println("-> beforeClass");
    }

    @BeforeEach
    void before() {
        System.out.println("--> before");
    }

    @AfterAll
    static void afterClass() {
        System.out.println("-> afterClass");
    }

    @AfterEach
    void after() {
        System.out.println("--> after");
    }

    @Test
    void test1() {
        System.out.println("---> TestJUnit5.test1");
    }

    @Test
    void test2() {
        System.out.println("---> TestJUnit5.test2");
    }
}