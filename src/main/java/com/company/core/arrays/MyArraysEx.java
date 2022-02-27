package com.company.core.arrays;

import java.util.Arrays;

/**
 * 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
 * 2. Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
 * 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
 * <p>
 * 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов)
 * заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно).
 * Определить элементы одной из диагоналей можно по следующему принципу: индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
 * <p>
 * 5. Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
 * <p>
 * 6. * Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
 * <p>
 * 7. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
 * **Примеры:
 * checkBalance([2, 2, 2, 1, 2, 2, ||| 10, 1]) → true, т.е. 2 + 2 + 2 + 1 + 2 + 2 = 10 + 1
 * checkBalance([1, 1, 1, ||| 2, 1]) → true, т.е. 1 + 1 + 1 = 2 + 1
 * граница показана символами |||, эти символы в массив не входят и не имеют никакого отношения к ИЛИ.
 * <p>
 * 8. *** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
 * при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
 * Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ]. При каком n в какую сторону сдвиг можете выбирать сами.
 */
class MyArraysEx {
    public static void main(String[] args) {
        method_1();
        method_2();
        method_3();
        method_4();
        method_5();
        method_6();
        method_7();
        method_8();
    }

    private static void method_8() {
        System.out.println("\nЗадание 8");
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, -1};
        System.out.print(Arrays.toString(arr));
        rotateArray(arr, 14);
        System.out.println(" cмещаем на 14 вправо --> " + Arrays.toString(arr));

        int[] arr2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, -1};
        System.out.print(Arrays.toString(arr2));
        rotateArray(arr2, -13);
        System.out.println(" cмещаем на 13 влево (-13) --> " + Arrays.toString(arr2));
    }

    private static void rotateArray(int[] arr, int step) {
        int stepsCount = Math.abs(step);
        // уменьшаем сложность алгоритма
        if (stepsCount > arr.length) stepsCount = stepsCount % arr.length;

        for (int i = 0; i < stepsCount; i++) {
            if (step > 0) rotateRight(arr);
            else rotateLeft(arr);
        }
    }

    private static void rotateRight(int[] arr) {
        int tmp = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;
    }

    private static void rotateLeft(int[] arr) {
        int tmp = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = tmp;
    }


    private static void method_7() {
        System.out.println("\nЗадание 7");
        int[] arr1 = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] arr2 = {1, 1, 1, 2, 5};
        int[] arr3 = {1, 1, 1, 5, 1};
        System.out.println(Arrays.toString(arr1) + " balance = " + checkBalance(arr1));
        System.out.println(Arrays.toString(arr2) + " balance = " + checkBalance(arr2));
        System.out.println(Arrays.toString(arr3) + " balance = " + checkBalance(arr3));
    }

    static boolean checkBalance(int[] arr) {
        int sumArray = 0;
        for (int i = 0; i < arr.length; i++)
            sumArray += arr[i];
        int searchValue = sumArray / 2;

        for (int i = 0; i < arr.length - 1; i++) {
            sumArray -= arr[i];
            if (sumArray == searchValue) return true;
            if (sumArray < searchValue) break;
        }

        return false;
    }

    private static void method_6() {
        System.out.println("\nЗадание 6");
        int[] arr = {2, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int min = arr[0];
        int max = arr[0];
        for (int a : arr) {
            if (a > max) max = a;
            if (a < min) min = a;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("max = " + max + ", min = " + min);
    }

    private static void method_5() {
        System.out.println("\nЗадание 5");
        int[] arr = initArray(15, -3);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] initArray(int len, int initialValue) {
        int[] newArray = new int[len];
        Arrays.fill(newArray, initialValue);
        return newArray;
    }


    private static void method_4() {
        System.out.println("\nЗадание 4");
        int size = 9;
        int[][] arr = new int[size][size];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j || (i + j) == (arr.length - 1)) arr[i][j] = 1;
                else arr[i][j] = 0;
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void method_3() {
        System.out.println("\nЗадание 3");
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.print(Arrays.toString(arr) + "  -->  ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] *= 2;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void method_2() {
        System.out.println("\nЗадание 2");
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void method_1() {
        System.out.println("\nЗадание 1");
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.print(Arrays.toString(arr) + "  -->  ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 0) ? 1 : 0;
        }
        System.out.println(Arrays.toString(arr));
    }


}
