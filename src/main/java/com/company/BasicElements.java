package com.company;

import com.company.arrays.ArrayExample;

import java.util.Arrays;
import java.util.Scanner;

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


    public static void main(String[] args) {

        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];

        }  // O(4 * n) , где 4 - это 4 действия, которые будут выполняться для каждого элемента
        System.out.println(Arrays.toString(arr));
        System.out.println("max= " + max);
        System.out.println("fibonacci= " + fibonacci(6));

        boolean a = false;
        boolean b = true;

        System.out.println("a & b = " + (a & b));
        System.out.println("a | b = " + (a | b));
        System.out.println(a ^ b);
        System.out.println("5! = " + factorial(5));

        System.out.println(sum(1, 2, 3));
        System.out.println(sum(4, 3, 4, 2, 3));
        System.out.println(sum(1));

        int i = inputInt();
    }

    public static Scanner scanner = new Scanner(System.in);

    // ввод числа с проверкой
    private static int inputInt() {
        while (true) {
            System.out.println("Введите число:");

            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println(scanner.next() + " - это не число");
        }
    }

    static int sum(int... a) {
        int rez = 0;
        for (int i : a) {
            rez += i;
        }
        return rez;
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
