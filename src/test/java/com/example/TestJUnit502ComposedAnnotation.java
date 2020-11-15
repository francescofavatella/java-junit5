package com.example;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class TestJUnit502ComposedAnnotation {

    @Test
    void noTagTest() {
        System.out.println("No Tag Test");
    }

    @Fast
    @Test
    void fastTest() {
        System.out.println("Fast Test");
    }

    @Slow
    @Test
    void slowTagTest() {
        System.out.println("Slow Tag Test");
    }

    @FastTest
    void combinedAnnotationTest() {
        System.out.println("Combined Annotation Test");
    }
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
@interface Fast {
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("slow")
@interface Slow {
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
@Test
@interface FastTest {
}