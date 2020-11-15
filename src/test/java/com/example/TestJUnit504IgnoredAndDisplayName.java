package com.example;


import org.junit.jupiter.api.*;

class TestJUnit504IgnoredAndDisplayName {

    @Test
    void myTest() {
        System.out.println("My Test");
    }

    @Disabled("I am broken")
    @Test
    void disabledTest() {
        System.out.println("Disabled demo");
    }

    @DisplayName("00 Test with fancy name")
    @Test
    void displayNameTest() {
        System.out.println("DisplayName demo");
    }

    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class Nested_TestClass {
        @Test
        void displayNameGeneration_Test() {
            System.out.println("DisplayNameGeneration demo");
        }
    }
}