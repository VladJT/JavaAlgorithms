package com.company;

import com.company.arrays.ArrayExample;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/***
 * О - Асимптотическая сложность (рост сложности алгоритма с ростом кол-ва вх. аргументов)
 * 1 = О(1) - константа
 * f(n) = O(log(n)) - логарифмический рост
 * f(n) = O(n) - линейный рост
 * f(n) = O(n * log(n)) - квазилинейный рост
 * f(n) = O(n^m) - полиномиальный рост
 * f(n) = O(2^m) - экспоненциальный рост
 * f(n) = O(n!) - рост факториальный
 */

public class BasicElements {

    // BiFunction является functional interface, представляющим оператор, который принимает 2 значения и возвращает 1.
    void l(){
        BiFunction<Integer, Integer, Integer> mul = (x, y) -> x * y;
        int rez = mul.apply(5,2);//10
    }


    // поиск самого короткого слова в строке и вывод его длины
    // "bitcoin take over the world maybe who knows perhaps" --> 3
    public static int findShort(String s) {
        return Stream.of(s.split(" "))
                .mapToInt(String::length)
                .min()
                .getAsInt();
    }


    // ввод числа с проверкой
    private static int inputInt() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите число:");

            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println(scanner.next() + " - это не число");
        }
    }



    public static int fibonacci(int n) {
        if (n <= 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int factorial(int n) {
        if (n <= 1) return 1;
        else return n * factorial(n - 1);
    }
}
