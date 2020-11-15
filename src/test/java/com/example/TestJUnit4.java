package com.example;

import org.junit.*;

public class TestJUnit4 {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("-> beforeClass");
    }

    @Before
    public void before() {
        System.out.println("--> before");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("-> afterClass");
    }

    @After
    public void after() {
        System.out.println("--> after");
    }

    @Test
    public void test1() {
        System.out.println("---> test1");
    }

    @Test
    public void test2() {
        System.out.println("---> test2");
    }

    @Ignore("Disabled test")
    @Test
    public void test3() {
        System.out.println("---> test3 ignored");
    }
}