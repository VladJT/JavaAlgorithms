package com.company.core.testjunit.junitandlogger;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class Method7Test {
    static Method7 method7;

    @BeforeAll
    static void mainStart() {
        System.out.println("--MAIN START--");
        method7 = new Method7();
    }

    @AfterAll
    static void mainEnd() {
        System.out.println("--MAIN END--");
    }


    @DisplayName("Тест проверки массива (размерность < 2)")
    @Test
    void testCheckArrayBalance() {
        Assertions.assertEquals(false, method7.checkBalance(new int[]{0}));
    }

    @DisplayName("Тест проверки массива (c параметрами)")
    @ParameterizedTest
    @MethodSource("data")
    void testCheckArrayBalanceWithParams(boolean result, int[] arr) {
        Assertions.assertEquals(result, method7.checkBalance(arr));
    }

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.arguments(true, new int[]{1, 2, 3, 4, 2}),
                Arguments.arguments(false, new int[]{1, 2, 1, 7, 9}),
                Arguments.arguments(false, new int[]{8, 3, -4, 0, 2}),
                Arguments.arguments(true, new int[]{1, 3, -7, -1, -2}),
                Arguments.arguments(false, new int[]{})
        );
    }
}