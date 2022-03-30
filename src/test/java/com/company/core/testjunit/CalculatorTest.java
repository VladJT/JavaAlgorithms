package com.company.core.testjunit;

import org.junit.After;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/*
Класс Assertions содержит в себе множество статических методов-проверок:
● assertTrue() – проверяет, что переданное логическое значение истинно;
● assertFalse() – проверяет, что переданное логическое значение ложно;
● fail() – метод для принудительного «провала» теста;
● assertEquals() - проверяет, что для объектов, переданных в качестве аргументов, метод equals возвращает true;
● assertNotEquals() – проверяет, что equals для объектов возвращает false;
● assertNull() – проверяет, что переданный в качестве аргумента объект равен null;
● assertNotNull() – проверяет, что переданный в качестве аргумента объект не равен null.

 */

class CalculatorTest {
    Calculator calc;

   @After
    public void finish(){
           System.out.println("ТЕСТИРОВАНИЕ ЗАВЕРШЕНО");
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    public void timeTest() {
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void init() {
        if (calc == null)
            calc = new Calculator();
    }

    @ParameterizedTest
    @MethodSource("dataForAddOperation")
    public void testAddOperation(int a, int b, int result) {
        Assertions.assertEquals(result, calc.add(a, b));
    }
    public static Stream<Arguments> dataForAddOperation() {
        List<Arguments> out = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int a = (int)(Math.random() * 1000);
            int b = (int)(Math.random() * 1000);
            int result = a + b;
            out.add(Arguments.arguments(a, b, result));
        }
        return out.stream();
    }



    @CsvSource({
            "1, 1, 2",
            "2, 3, 6",
            "7, 5, 12",
            "12, 12, 24"
    })
    @ParameterizedTest
    void add(int a, int b, int result)  {
        Assertions.assertEquals(result, calc.add(a, b));
    }

    @Test
    void sub() {
        Assertions.assertEquals(3, calc.sub(8, 5));
    }

    @Test
    void mul() {
        Assertions.assertEquals(12, calc.mul(2, 6));
    }

    @Test
    @Disabled("Деление на 0 пока тестировать не нужно")
    void divZero() {
        Assertions.assertEquals(0, calc.div(12, 0));
        Assertions.fail("bad");
    }

    @Test
    void div() {
        Assertions.assertEquals(2, calc.div(12, 6));
    }
}