package com.company.sort;

public class InsertionSort {


    public static int sort(int[] array) {
        int countIteration = 0;
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            int value = array[i];

            while (j >= 0 && array[j] > value) {
                array[j+1] = array[j];
                j--;
                countIteration++;
            }
            array[j+1] = value;
        }

        return countIteration;
    }
}
