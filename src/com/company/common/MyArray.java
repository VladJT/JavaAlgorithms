package com.company.common;

import java.util.Random;

public class MyArray {

    // размер массива
    int size;

    // массив целых чисел
    int[] array;

    public int[] getArray() {
        return array;
    }

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

    public void initUniqueArray(int step) {
        Random r = new Random();
        array[0] = r.nextInt(step) + 1;
        for (int i = 1; i < array.length; i++) {
            array[i] = r.nextInt(step) + 1 + array[i - 1];
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
        for (int i = 0; i < array.length; i++) {
            System.out.print("[" + i + "]:" + array[i] + "  ");
            //if (i % 20 == 0 && i!=0) System.out.println();
        }
        System.out.println();
    }

}
