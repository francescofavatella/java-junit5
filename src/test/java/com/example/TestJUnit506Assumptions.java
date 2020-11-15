package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class TestJUnit506Assumptions {
    private static String ENV = "DEV";

    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(ENV));
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(ENV),
                () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(ENV),
                () -> {
                    // perform these assertions only on the CI server
                    assertEquals(2, divide(4, 2));
                });

        // perform these assertions in all environments
        assertEquals(42, multiply(6, 7));
    }

    static float divide(int a, int b) {
        return a / b;
    }

    static int multiply(int a, int b) {
        return a * b;
    }
}