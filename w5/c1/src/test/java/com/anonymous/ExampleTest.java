package com.anonymous;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ExampleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("START");
    }

    @Test
    void testOne() {
        System.out.println("testOne");
    }

    @Test
    void testTwo() {
        System.out.println("testTwo");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("END");
    }
}
