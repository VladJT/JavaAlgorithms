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

// функциональный интерфейс
interface Expression{
    long call(long a, long b);
}

class BasicElements {
    // функция как параметр
    public static long[] operArray(Expression operator, long[] arr, long init) {
        long[] newArr = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = operator.call(arr[i], init);
            init = newArr[i];
        }
        return newArr;
    }
    public static long som(long a, long b) {
        return a + b;
    }
    public static long maxi(long a, long b) {
        return a > b ? a : b;
    }
    public static void main(String[] args) {
        long[] a = new long[]{18, 69, -90, -78, 65, 40};
        System.out.println(Arrays.toString(operArray(BasicElements::som, a, 18)));
    }

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
