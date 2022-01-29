package com.company.arrays;

import java.util.Arrays;

public class ArrayExample {

    public static void main(String[] args) {

        /**
         * 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
         * 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
         * 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], пройти по нему циклом, и числа, меньшие 6, умножить на 2;
         */

        // -1
        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        printArray(arr1);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == 0) arr1[i] = 1;
            else arr1[i] = 0;
        }
        printArray(arr1);

        // -2
        int[] arr2 = new int[8];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i * 3;
        }
        printArray(arr2);

        // -3
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(arr3);
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) arr3[i] *= 2;
        }
        printArray(arr3);

        /**
         * 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
         * 5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
         * */
        System.out.println("------------------------");
        // -4
        int[][] arr4 = new int[9][9];
        initMatrixArray(arr4);
        printMatrixArray(arr4);

        //-5
        int[] arr5 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int min = arr5[0];
        int max = arr5[0];
        for (int a : arr5) {
            if (a > max) max = a;
            if (a < min) min = a;
        }
        printArray(arr5);
        System.out.println("max= " + max + ", min= " + min);

        /**
         * 6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
         * если в массиве есть место, в котором сумма левой и правой части массива равны.
         * Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false, checkBalance ([10, || 10]) → true,
         * граница показана символами ||, эти символы в массив не входят;
         * 7. *Написать метод, которому на вход подается одномерный массив и число n (может быть положительным или отрицательным), при этом метод должен сместить все элементы массива на n позиций. Нельзя пользоваться вспомогательными массивами.
         */
        // -6
        int[] arr6_1 = {1, 1, 1, 2, 1};
        int[] arr6_2 = {2, 1, 1, 2, 1};
        int[] arr6_3 = {10, 10};
        System.out.println("[1, 1, 1, || 2, 1] → " + checkBalance(arr6_1));
        System.out.println("[2, 1, 1, 2, 1] → " + checkBalance(arr6_2));
        System.out.println("[10, || 10] → " + checkBalance(arr6_3));

        //-7
        System.out.println("------------------------");
        System.out.println("Исходный массив:");
        int[] arr7 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, -1};
        printArray(arr7);
        System.out.println("Смещение на -2:");
        printArray(moveArray(arr7, -2));

        System.out.println("Вернем в исходное, сместив на +2:");
        printArray(moveArray(arr7, 2));

        System.out.println("Сдвинем на +3:");
        printArray(moveArray(arr7, 3));


    }

    static int[] moveArray(int[] arr, int n) {
        int pos;
        int x;
        if (n == 0) return arr;

        // двигаем массив влево или вправо n раз
        int steps = Math.abs(n);
        for (int step = 1; step <= steps; step++) {
            if (n > 0) {
                x = arr[arr.length - 1];
                for (int i = arr.length - 1; i > 0; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[0] = x;
            } // n>0
            if (n < 0) {
                x = arr[0];
                for (int i = 0; i < arr.length - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[arr.length - 1] = x;
            } // n<0
        }
        return arr;
    }

    static boolean checkBalance(int[] arr) {
        int sumLeft = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            sumLeft += arr[i];
            int sumRight = 0;
            for (int j = (i + 1); j < arr.length; j++) {
                sumRight += arr[j];
            }
            if (sumLeft == sumRight) return true;
        }
        return false;
    }


    static void printArray(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    static void initMatrixArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j || (i + j) == (arr.length - 1)) arr[i][j] = 1;
            }
        }
    }

    static void printMatrixArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}