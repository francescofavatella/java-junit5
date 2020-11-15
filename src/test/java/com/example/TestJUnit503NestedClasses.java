package com.example;


import org.junit.jupiter.api.*;

class TestJUnit503NestedClasses {

    @Nested
    @DisplayName("Nested Test Class 1")
    class NestedTestClass1 {
        @Test
        void nestedTest1() {
            System.out.println("nestedTest1");
        }
    }

    @Nested
    @DisplayName("Nested Test Class 2")
    class NestedTestClass2 {
        @Test
        void nestedTest2() {
            System.out.println("nestedTest2");
        }
    }
}