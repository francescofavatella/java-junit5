package com.example;

import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

class TestJUnit505Assertions {
    private static final String actualName = "name";
    private static final String actualSurname = "surname";

    @Test
    void standardAssertions() {
        assertEquals("myName", actualName);
        assertEquals("mySurname", actualSurname,
                "The optional failure message is now the last parameter");
        assertTrue(false, () -> "Assertion messages can be lazily evaluated");
    }

    @Test
    void groupedAssertions() {
        assertAll("person",
                () -> assertEquals("anotherName", actualName),
                () -> assertEquals("anotherSurname", actualSurname)
        );
    }

    @Test
    void dependentAssertions() {
        String address = null;
        assertAll("properties",
                () -> {
                    assertNotNull(actualName, "name is null");
                    assertAll("TEST 1",
                            () -> assertEquals("anotherName", actualName),
                            () -> assertEquals("anotherSurname", actualSurname)
                    );
                },
                () -> {
                    assertNotNull(address, "address is null");
                    assertAll("TEST 2",
                            () -> assertEquals("anotherName", actualName),
                            () -> assertEquals("anotherSurname", actualSurname)
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () -> divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        assertTimeout(ofSeconds(2), () -> "Perform task that takes less than 2 seconds.");
    }

    @Test
    void timeoutNotExceededWithResult() {
        String actualResult = assertTimeout(ofSeconds(2), () -> "a result");
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutExceeded() {
        assertTimeout(ofMillis(10), () -> Thread.sleep(100));
    }

    static float divide(int a, int b) {
        return a / b;
    }
}