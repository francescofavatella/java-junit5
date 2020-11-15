package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TestJUnit511ParametrizedTests {

    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    void isOne(String candidate) {
        assertEquals("one", candidate);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSource(int argument) {
        Assertions.assertTrue(argument > 0 && argument < 4);
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @MethodSource("com.example.StringsProviders#tinyStrings")
    void testWithExternalMethodSource(String tinyString) {
        assertTrue(tinyString.length() < 2, "String is too long");
    }
}

class StringsProviders {
    static Stream<String> tinyStrings() {
        return Stream.of(".", "oo", "OOO");
    }
}