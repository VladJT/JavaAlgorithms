package com.company.core.testjunit.junitandlogger;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Method8Test {

    static Method8 method8;

    @BeforeAll
    static void mainStart() {
        System.out.println("MAIN START");
        method8 = new Method8();
    }

    @AfterAll
    static void mainEnd() {
        System.out.println("MAIN END");
    }

    @CsvSource({
            "9 -1 1 5 3 2 11 4 5 2 4 8, 1 5 3 2 11 4 5 2 4 8 9 -1, 14",
            "4 5 1 2 3, 1 2 3 4 5, 2",
            "3 4 65 43 -1 0 3, 3 4 65 43 -1 0 3, 7",
            "9, 9, 10",
            "6 7 1 2 3 4 5, 1 2 3 4 5 6 7, 702"
    })
    @DisplayName("Тест поворота массива вправо (с параметрами)")
    @ParameterizedTest
    void rotateArrayToRight(String stringExpected, String stringActual, int step) {
        int[] expected = Arrays.stream(stringExpected.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] actual = Arrays.stream(stringActual.split(" ")).mapToInt(Integer::parseInt).toArray();

        Assertions.assertArrayEquals(expected, method8.rotateArray(actual, step));
    }

    @DisplayName("Простой сдвиг вправо на 1")
    @Test
    void rotateArrayToRightSimple() {
        Assertions.assertArrayEquals(new int[]{3, 1, 2}, method8.rotateArray(new int[]{1, 2, 3}, 1));
    }


    @DisplayName("Тест поворота массива влево (с параметрами)")
    @MethodSource("data")
    @ParameterizedTest
    void rotateArrayToLeft(int[] expected, int[] actual, int step) {
        Assertions.assertArrayEquals(expected, method8.rotateArray(actual, step));
    }

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.arguments(new int[]{4, 2, 1, 2, 3}, new int[]{2, 3, 4, 2, 1}, -2),
                Arguments.arguments(new int[]{2, 3, 1}, new int[]{1, 2, 3}, -1),
                Arguments.arguments(new int[]{5, 3, 2, 11, 4, 5, 2, 4, 8, 9, -1, 1}, new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, -1}, -13)

        );
    }

}