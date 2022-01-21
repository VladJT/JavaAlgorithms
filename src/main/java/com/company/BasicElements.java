package main.java.com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
        ArrayExample.printArray(arr);
        System.out.println("max= " + max);
        System.out.println("fibonacci= " + fibonacci(6));
    }


    public static int fibonacci(int n) {
        if (n <= 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
