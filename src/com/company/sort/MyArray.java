package com.company.sort;

import java.util.Random;

public class MyArray {

    // размер массива
    int size;

    // массив целых чисел
    int[] array;

    public MyArray(int _size) {
        size = _size;
        array = new int[size];
    }

    // инициализировать массив случайными числами от 1 до maxValue
    public void initArray(int maxValue) {
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(maxValue) + 1;
        }
    }

    // поменять местами 2 элемента массива
    public void swap(int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    // вывод массива на экран
    public void printArray() {
        for (int a : array) {
            System.out.print(a + " ");
        }
        System.out.println();
    }


}
